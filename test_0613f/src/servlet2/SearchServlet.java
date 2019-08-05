package servlet2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

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

		//セッション取得の準備
		HttpSession session = request.getSession();

		// セッションから情報を取得
		String id = (String) session.getAttribute("masterId");
		Integer year = (Integer) session.getAttribute("year");
		Integer month = (Integer) session.getAttribute("month");

		// 入力情報の取得
		final String name = request.getParameter("name");

		// DAOの宣言
		SearchDao search = new SearchDao();
		LoginUser login = new LoginUser();
		TotalMDao totalM = new TotalMDao();

		// 自分以外の全ユーザの名前を取得
		List<String> userList = search.findNameAll(id);

		// レーベンシュタイン距離による評価関数
		LevenshteinDistance dis =  new LevenshteinDistance();
		ToIntFunction<String> dist = s -> {
			return (int) (dis.getDistance(name, s) * 100);
			};

		// 該当の名前か近しい名前を取得
		String closest = userList.stream()
				.max(Comparator.comparingInt(dist))
				.orElse(name);
		System.out.println("もしかして " + closest);

		// ヒットしたユーザの情報を取得
		List<TotalM> list = totalM.findAllForAdmin(closest, year, month);

		// リストのサイズを取得
		int size = list.size();

		// String型のListの宣言
		List<String> divisionList = new ArrayList<>();

		// リストのレコード分繰り返す
		for(int i = 0; i < size; i++) {
			// 区間の取得
			String division = list.get(i).getDivision();
			// 区間が「片道」？
			if(division.equals("片道")) {
				// 往復をセット
				division = "往復";
				divisionList.add(division);
			// 区間が「往復」？
			} else {
				// 片道をセット
				division = "片道";
				divisionList.add(division);
			}
		}
		// ヒットしたユーザの情報をセット
		session.setAttribute("id", list.get(0).getId());
		session.setAttribute("name", closest);
		session.setAttribute("list", list);
		session.setAttribute("divisionList", divisionList);

		// home.jspに遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("/business/home.jsp");
		dispatch.forward(request, response);
	}

}
