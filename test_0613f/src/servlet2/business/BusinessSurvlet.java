package servlet2.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.TotalM;
import test_0613f.business.TotalMDao;

/**
 * Servlet implementation class BusinessSurvlet
 */
@WebServlet("/business")
public class BusinessSurvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusinessSurvlet() {
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
		Integer month = cal.get(Calendar.MONTH) + 1;

		// 権限が管理者？
		if(auth == 1) {
			// セッションに情報をセット
			session.setAttribute("year", year);
			session.setAttribute("month", month);

			// 検索画面へ遷移
			response.sendRedirect("./business/search.jsp");
		// 権限が担当者？
		} else {
			// DAOの宣言
			TotalMDao tmd = new TotalMDao();

			// 該当年月の出張精算データ取得
			List<TotalM> list = tmd.findAllByMonthForId(id, year, month, 0);

			// ページングなしでのレコード数取得
			int count = tmd.countRow(id, year, month);

			// リストのサイズ取得
			int size = list.size();

			// String型のListの宣言
			List<String> divisionList = new ArrayList<>();

			// リストのレコード分繰り返す
			for(int i = 0; i < size; i++) {
				// 区分を取得
				String division = list.get(i).getDivision();

				// 区分が片道？
				if(division.equals("片道")) {
					// 往復をセット
					division = "往復";
					divisionList.add(division);
				// 区分が往復？
				} else {
					// 片道をセット
					division = "片道";
					divisionList.add(division);
				}
			}

			// ページング設定
			int number = (count + 5 - 1) / 5;

			// セッションに情報をセット
			session.setAttribute("year", year);
			session.setAttribute("month", month);
			session.setAttribute("currentpage", 1);
			session.setAttribute("size", size);
			session.setAttribute("number", number);
			session.setAttribute("list", list);
			session.setAttribute("divisionList", divisionList);
			session.setAttribute("nolist", "登録されているデータがありません。");

			// 出張精算画面へ遷移
			response.sendRedirect("./business/home.jsp");
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
