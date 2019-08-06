package servlet2;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.lucene.search.spell.LevenshteinDistance;

import entity.TotalM;
import test_0613f.SearchDao;
import test_0613f.TotalMDao;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/business/Search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け回避
		request.setCharacterEncoding("UTF-8");

		// セッション取得の準備
		HttpSession session = request.getSession();

		// セッションから情報を取得
		String masterId = (String) session.getAttribute("masterId");
		String masterName = (String) session.getAttribute("masterName");
		Integer year = (Integer) session.getAttribute("year");
		Integer month = (Integer) session.getAttribute("month");

		// 「ID検索」が押された？
		if(request.getParameter("idSearch") != null) {
			// 入力情報の取得
			final String id = request.getParameter("id");

			// DAOの宣言
			SearchDao search = new SearchDao();
			TotalMDao totalM = new TotalMDao();

			// 入力フォームが空欄？
			if(id == null || id.equals("")) {
				// 全員分のリスト取得
				//List<TotalM> list = totalM.findAllUserListByMonth(year, month);

				// リストにあるレコードの人数を取得
				//int size = search.findIdDistinct(year, month).size();

				// 今月の出張清算データがある人物のIDを取得
				List<String> idList = search.findIdDistinct(year, month);

				// 今月の出張清算データがある人物の名前を取得
				List<String> nameList = search.findNameDistinct(year, month);

				// データが1件でもある？
				if(idList.size() > 0 && nameList.size() > 0) {
					// 今月分の全社員の出張精算データを取得
					Map<String, List<TotalM>> map = idList.stream()
							.collect(Collectors.toMap(
									s -> s,
									s -> totalM.findAllByMonthForIdFromAdmin(s, year, month)));

					// セッションに情報をセット
					session.setAttribute("map", map);
					session.setAttribute("nameList", nameList);
				// データが1件もない？
				} else {
					// セッションに情報をセット
					session.setAttribute("nomap", "登録されているデータがありません。");
				}

				// homeAdmin.jspに遷移
				RequestDispatcher dispatch = request.getRequestDispatcher("/business/homeAdmin.jsp");
				dispatch.forward(request, response);
			// 入力フォームに入力あり？
			} else {
				// 自分以外の全ユーザのIDを取得
				List<String> userList = search.findIdAll(masterName);

				// レーベンシュタイン距離による評価関数
				LevenshteinDistance dis =  new LevenshteinDistance();
				ToIntFunction<String> dist = s -> {
					return (int) (dis.getDistance(id, s) * 100);
					};

				// 該当のIDか近しいIDを取得
				String closest = userList.stream()
						.max(Comparator.comparingInt(dist))
						.orElse(id);

				// ヒットしたユーザの情報を取得
				List<TotalM> list = totalM.findAllByMonthForId(closest, year, month, 0);

				// リストのサイズを取得
				//int size = list.size();

				// ヒットしたユーザの情報をセッションにセット
				session.setAttribute("id", list.get(0).getId());
				session.setAttribute("name", closest);
				session.setAttribute("list", list);

				// home.jspに遷移
				RequestDispatcher dispatch = request.getRequestDispatcher("/business/home.jsp");
				dispatch.forward(request, response);
			}
		// 「名前検索」が押された？
		} else if(request.getParameter("nameSearch") != null) {
			// 入力情報の取得
			final String name = request.getParameter("name");

			// DAOの宣言
			SearchDao search = new SearchDao();
			TotalMDao totalM = new TotalMDao();

			// 入力フォームが空欄？
			if(name == null || name.equals("")) {
				// 全員分のリスト取得
				//List<TotalM> list = totalM.findAllUserListByMonth(year, month);

				// リストにあるレコードの人数を取得
				//int size = search.findIdDistinct(year, month).size();

				// 今月の出張清算データがある人物のIDを取得
				List<String> idList = search.findIdDistinct(year, month);

				// 今月の出張清算データがある人物の名前を取得
				List<String> nameList = search.findNameDistinct(year, month);

				// データが1件でもある？
				if(idList.size() > 0 && nameList.size() > 0) {
					// 今月分の全社員の出張精算データを取得
					Map<String, List<TotalM>> map = idList.stream()
							.collect(Collectors.toMap(
									s -> s,
									s -> totalM.findAllByMonthForIdFromAdmin(s, year, month)));

					// セッションに情報をセット
					session.setAttribute("map", map);
					session.setAttribute("nameList", nameList);
				// データが1件もない？
				} else {
					// セッションに情報をセット
					session.setAttribute("nomap", "登録されているデータがありません。");
				}

				// homeAdmin.jspに遷移
				RequestDispatcher dispatch = request.getRequestDispatcher("/business/homeAdmin.jsp");
				dispatch.forward(request, response);
			// 入力フォームに入力あり？
			} else {
				// 自分以外の全ユーザの名前を取得
				List<String> userList = search.findNameAll(masterId);

				// レーベンシュタイン距離による評価関数
				LevenshteinDistance dis =  new LevenshteinDistance();
				ToIntFunction<String> dist = s -> {
					return (int) (dis.getDistance(name, s) * 100);
					};

				// 該当の名前か近しい名前を取得
				String closest = userList.stream()
						.max(Comparator.comparingInt(dist))
						.orElse(name);

				// ヒットしたユーザの情報を取得
				List<TotalM> list = totalM.findAllByMonthForName(closest, year, month, 0);

				// リストのサイズを取得
				//int size = list.size();

				// ヒットしたユーザの情報をセッションにセット
				session.setAttribute("id", list.get(0).getId());
				session.setAttribute("name", closest);
				session.setAttribute("list", list);

				// home.jspに遷移
				RequestDispatcher dispatch = request.getRequestDispatcher("/business/home.jsp");
				dispatch.forward(request, response);
			}
		}
	}
}
