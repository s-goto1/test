package servlet2.vacation;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Vacation;
import test_0613f.vacation.VacationDao;

/**
 * Servlet implementation class VacationServlet
 */
@WebServlet("/vacation")
public class VacationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VacationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け回避
		request.setCharacterEncoding("UTF-8");

		//セッション取得の準備
		HttpSession session = request.getSession();

		// セッションから情報を取得
		String id = (String) session.getAttribute("id");
		Integer auth = (Integer) session.getAttribute("auth");

		// 現在年月を取得
		Calendar cal = Calendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
		Integer fromMonth = cal.get(Calendar.MONTH) + 1;

		// 権限が管理者？
		if(auth == 1) {
			// セッションに情報をセット
			session.setAttribute("year", year);
			session.setAttribute("fromMonth", fromMonth);

			// 検索画面へ遷移
			response.sendRedirect("./vacation/search.jsp");
		// 権限が担当者？
		} else {
			// DAOの宣言
			VacationDao dao = new VacationDao();

			// 該当年月の休暇申請データ取得
			List<Vacation> list = dao.findAllByMonthForId(id, year, 0);

			// ページングなしでのレコード数取得
			int count = dao.countRow(id, year);

			// ページング設定
			int number = (count + 5 - 1) / 5;

			// セッションに情報をセット
			session.setAttribute("year", year);
			session.setAttribute("fromMonth", fromMonth);
			session.setAttribute("currentpage", 1);
			session.setAttribute("number", number);
			session.setAttribute("list", list);
			session.setAttribute("nolist", "登録されているデータがありません。");

			// 休暇申請画面へ遷移
			response.sendRedirect("./vacation/home.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
