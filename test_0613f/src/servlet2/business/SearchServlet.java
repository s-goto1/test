package servlet2.business;

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
import test_0613f.LoginUser;
import test_0613f.business.SearchDao;
import test_0613f.business.TotalMDao;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文字化け回避
		request.setCharacterEncoding("UTF-8");

		// セッション取得の準備
		HttpSession session = request.getSession();

		// セッションから情報を取得
		//String masterId = (String) session.getAttribute("masterId");
		String masterName = (String) session.getAttribute("masterName");
		Integer year = (Integer) session.getAttribute("year");
		Integer month = (Integer) session.getAttribute("month");

		// 「ID検索」が押された？
		if (request.getParameter("idSearch") != null) {
			// 入力情報の取得
			final String id = request.getParameter("id");

			// DAOの宣言
			SearchDao search = new SearchDao();
			TotalMDao totalM = new TotalMDao();
			LoginUser user = new LoginUser();

			// 入力フォームが空欄？
			if (id == null || id.equals("")) {
				// 全員分のリスト取得
				//List<TotalM> list = totalM.findAllUserListByMonth(year, month);

				// リストにあるレコードの人数を取得
				//int size = search.findIdDistinct(year, month).size();

				// 今月の出張清算データがある人物のIDを取得
				List<String> idList = search.findIdDistinct(year, month);

				// 今月の出張清算データがある人物の名前を取得
				List<String> nameList = search.findNameDistinct(year, month);

				// データが1件でもある？
				if (idList.size() > 0 && nameList.size() > 0) {
					// 変数宣言
					int index = 0;

					// リストのサイズを取得
					int size = idList.size();

					// ページ数を取得
					int number = (size + 5 - 1) / 5;

					// 5人以上いる？
					if (size >= 5) {
						// toIndexの値を5に固定
						index = 5;
						// 5人未満しかいない？
					} else {
						// toIndexの値を人数でセット
						index = size;
					}

					// 1度に表示するユーザ数を制限
					List<String> idListLimit = idList.subList(0, index);

					// 今月分の規定数の社員の出張精算データを取得
					Map<String, List<TotalM>> map = idListLimit.stream()
							.collect(Collectors.toMap(
									s -> s,
									s -> totalM.findAllByMonthForIdFromAdmin(s, year, month)));

					// セッションに情報をセット
					session.setAttribute("currentpage", 1);
					session.setAttribute("size", size);
					session.setAttribute("number", number);
					session.setAttribute("map", map);
					session.setAttribute("idList", idList);
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
				LevenshteinDistance dis = new LevenshteinDistance();
				ToIntFunction<String> dist = s -> {
					return (int) (dis.getDistance(id, s) * 100);
				};

				// 該当のIDか近しいIDを取得
				String closest = userList.stream()
						.max(Comparator.comparingInt(dist))
						.orElse(id);

				// レーベンシュタイン距離が離れすぎている？
				if (dis.getDistance(id, closest) * 100 < 40) {
					// リクエストに情報をセット
					request.setAttribute("error", "近しいIDが存在しません。文字数が少ない場合にはもう少し長く、"
							+ "文字数が多い場合にはもう少し短くして再度検索してみて下さい。"
							+ "あるいは、もう少し具体的な内容の文字列で検索してみて下さい。");

					// search.jspに遷移
					RequestDispatcher dispatch = request.getRequestDispatcher("/business/search.jsp");
					dispatch.forward(request, response);
					// レーベンシュタイン距離は適切な長さ？
				} else {
					// ヒットしたユーザの情報を取得
					List<TotalM> list = totalM.findAllByMonthForId(closest, year, month, 0);

					if (list.size() > 0 && list.size() > 0) {

						// ヒットしたユーザの情報を元にそのユーザの名前を取得
						String name = user.findUser(closest).getName();

						// ページングなしでのレコード数取得
						int count = totalM.countRow(closest, year, month);

						// ページング設定
						int number = (count + 5 - 1) / 5;

						// リストのサイズを取得
						//int size = list.size();

						// ヒットしたユーザの情報をセッションにセット
						session.setAttribute("id", closest);
						session.setAttribute("name", name);
						session.setAttribute("currentpage", 1);
						session.setAttribute("number", number);
						session.setAttribute("list", list);

					} else {
						// セッションに情報をセット
						session.setAttribute("nolist", "登録されているデータがありません。");
					}
					// home.jspに遷移
					RequestDispatcher dispatch = request.getRequestDispatcher("/business/home.jsp");
					dispatch.forward(request, response);
				}
			}
			// 「名前検索」が押された？
		}
		if (request.getParameter("nameSearch") != null) {
			// 入力情報の取得
			final String name = request.getParameter("name");

			// DAOの宣言
			SearchDao search = new SearchDao();
			TotalMDao totalM = new TotalMDao();

			// 入力フォームが空欄？
			if (name == null || name.equals("")) {
				// 全員分のリスト取得
				//List<TotalM> list = totalM.findAllUserListByMonth(year, month);

				// リストにあるレコードの人数を取得
				//int size = search.findIdDistinct(year, month).size();

				// 今月の出張清算データがある人物のIDを取得
				List<String> idList = search.findIdDistinct(year, month);

				// 今月の出張清算データがある人物の名前を取得
				List<String> nameList = search.findNameDistinct(year, month);

				// データが1件でもある？
				if (idList.size() > 0 && nameList.size() > 0) {
					// 変数宣言
					int index = 0;

					// リストのサイズを取得
					int size = nameList.size();

					// ページ数を取得
					int number = (size + 5 - 1) / 5;

					// 5人以上いる？
					if (size >= 5) {
						// toIndexの値を5に固定
						index = 5;
						// 5人以下未満しかいない？
					} else {
						// toIndexの値を人数でセット
						index = size;
					}

					// 1度に表示するユーザ数を制限
					List<String> nameListLimit = nameList.subList(0, index);

					// 今月分の規定数の社員の出張精算データを取得
					Map<String, List<TotalM>> map = nameListLimit.stream()
							.collect(Collectors.toMap(
									s -> s,
									s -> totalM.findAllByMonthForNameFromAdmin(s, year, month)));

					// セッションに情報をセット
					session.setAttribute("currentpage", 1);
					session.setAttribute("size", size);
					session.setAttribute("number", number);
					session.setAttribute("map", map);
					session.setAttribute("idList", idList);
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
				List<String> userList = search.findNameAll();

				// レーベンシュタイン距離による評価関数
				LevenshteinDistance dis = new LevenshteinDistance();
				ToIntFunction<String> dist = s -> {
					return (int) (dis.getDistance(name, s) * 100);
				};

				// 該当の名前か近しい名前を取得
				String closest = userList.stream()
						.max(Comparator.comparingInt(dist))
						.orElse(name);

				// レーベンシュタイン距離が離れすぎている？
				if (dis.getDistance(name, closest) * 100 < 40) {
					// リクエストに情報をセット
					request.setAttribute("error", "近しい名前が存在しません。文字数が少ない場合にはもう少し長く、"
							+ "文字数が多い場合にはもう少し短くして再度検索してみて下さい。"
							+ "あるいは、もう少し具体的な内容の文字列で検索してみて下さい。");

					// search.jspに遷移
					RequestDispatcher dispatch = request.getRequestDispatcher("/business/search.jsp");
					dispatch.forward(request, response);
					// レーベンシュタイン距離は適切な長さ？
				} else {
					// ヒットしたユーザの情報を取得
					List<TotalM> list = totalM.findAllByMonthForName(closest, year, month, 0);



					if (list.size() > 0 && list.size() > 0) {
						// ヒットしたユーザの情報を元にそのユーザのIDを取得
						String id = list.get(0).getId();

						// ページングなしでのレコード数取得
						int count = totalM.countRow(id, year, month);

						// ページング設定
						int number = (count + 5 - 1) / 5;

						// リストのサイズを取得
						//int size = list.size();

						// ヒットしたユーザの情報をセッションにセット
						session.setAttribute("id", id);
						session.setAttribute("name", closest);
						session.setAttribute("currentpage", 1);
						session.setAttribute("number", number);
						session.setAttribute("list", list);

					} else {
						// セッションに情報をセット
						session.setAttribute("nolist", "登録されているデータがありません。");
					}

					// home.jspに遷移
					RequestDispatcher dispatch = request.getRequestDispatcher("/business/home.jsp");
					dispatch.forward(request, response);
				}
			}
		}
	}
}
