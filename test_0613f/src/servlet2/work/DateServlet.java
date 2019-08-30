package servlet2.work;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ajd4jp.AJD;
import ajd4jp.AJDException;
import ajd4jp.Month;
import ajd4jp.OffProvider;
import ajd4jp.Week;
import entity.Work;
import test_0613f.vacation.SearchDao;
import test_0613f.work.WorkDao;
import util.NewYear;

/**
 * Servlet implementation class DateServlet
 */
@WebServlet("/work/Date")
public class DateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateServlet() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け防止
		request.setCharacterEncoding("UTF-8");

		// セッション情報取得の準備
		HttpSession session = request.getSession();

		// セッションから情報を取得
		String id = (String) session.getAttribute("id");
		Map<String, List<Work>> map = (Map<String, List<Work>>) session.getAttribute("map");

		// 入力値を取得
		String year = request.getParameter("year");
		String month = request.getParameter("month");

		// 型変換
		Integer y = Integer.valueOf(year);
		Integer m = Integer.valueOf(month);

		// DAOの宣言
		WorkDao work = new WorkDao();
		SearchDao search = new SearchDao();

		// homeAdmin.jspから遷移した？
		if(map != null) {
			// 一旦mapのセッションを空に
			session.setAttribute("map", "");

			// 今月の出勤退勤データがある人物のIDを取得
			List<String> idList = search.findIdDistinct(y);

			// 今月の出勤退勤データがある人物の名前を取得
			List<String> nameList = search.findNameDistinct(y);

			// データが1件でもある？
			if(idList.size() > 0 && nameList.size() > 0) {
				// 今月分の全社員の出勤退勤データを取得
				map = idList.stream()
						.collect(Collectors.toMap(
								s -> s,
								s -> work.findAllByMonthForId(s, y, m)));

				// セッションに情報をセット
				session.setAttribute("map", map);
				session.setAttribute("nameList", nameList);
			// データが1件もない？
			} else {
				// セッションに情報をセット
				session.setAttribute("nomap", "登録されているデータがありません。");
			}

			// セッションに情報をセット
			session.setAttribute("year", y);
			session.setAttribute("month", m);

			// homeAdmin.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/vacation/homeAdmin.jsp");
			dispatch.forward(request, response);
		// home.jspから遷移した？
		} else {
			// 該当年月の出勤退勤データ取得
			List<Work> list = work.findAllByMonthForId(id, y, m);

			// オブジェクト生成
			LocalDate localDate = LocalDate.of(y, m, 1);

			// 日数を取得
			int lastDay = localDate.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

			// String型のListを生成
			List<String> weekList = new ArrayList<>();

			// 土日祝日を休日とする
			OffProvider myCompanyOff = new OffProvider(true, Week.SATURDAY, Week.SUNDAY);

			try {
				// 12/29～1/3を年末年始休暇とする
				AJD from = new AJD(2000, 12, 29);
				AJD to = new AJD(2000, 1, 3);
				myCompanyOff.addOffEveryYear(from, to, new NewYear());

				// Month型の年月を取得
				Month mon = new Month(y, m);

				// 該当月の日数分繰り返す
				for(AJD date : mon.getDays()) {
					// 曜日をリストにセット
					weekList.add(date.getWeek().getJpName());
				}
			} catch(AJDException e) {
				e.printStackTrace();
			}

			// ページング設定
			int number = (lastDay + 7 - 1) / 7;

			// セッションに情報をセット
			session.setAttribute("currentpage", 1);
			session.setAttribute("year", y);
			session.setAttribute("month", m);
			session.setAttribute("number", number);
			session.setAttribute("indexNum", 0);
			session.setAttribute("lastNum", 6);
			session.setAttribute("lastDay", lastDay);
			session.setAttribute("list", list);
			session.setAttribute("weekList", weekList);

			// home.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/work/home.jsp");
			dispatch.forward(request, response);
		}
	}
}
