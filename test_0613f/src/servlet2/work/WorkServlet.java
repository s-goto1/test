package servlet2.work;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import entity.TimeTable;
import entity.Work;
import test_0613f.work.InsertDao;
import test_0613f.work.TimeTableDao;
import test_0613f.work.WorkDao;
import util.NewYear;

/**
 * Servlet implementation class WorkServlet
 */
@WebServlet("/work")
public class WorkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け回避
		request.setCharacterEncoding("UTF-8");

		// セッション取得の準備
		HttpSession session = request.getSession();

		// セッションから情報を取得
		String id = (String) session.getAttribute("id");
		Integer auth = (Integer) session.getAttribute("auth");

		// 現在年月を取得
		Calendar cal = Calendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
		Integer month = cal.get(Calendar.MONTH) + 1;
		Integer day = cal.get(Calendar.DAY_OF_MONTH);

		// 権限が管理者？
		if(auth == 1) {
			// セッションに情報をセット
			session.setAttribute("year", year);
			session.setAttribute("month", month);
			session.setAttribute("nolist", "登録されているデータがありません。");

			// 検索画面へ遷移
			response.sendRedirect("./work/search.jsp");
		// 権限が担当者？
		} else {
			// DAOの宣言
			//LoginUser user = new LoginUser();
			WorkDao work = new WorkDao();
			InsertDao insert = new InsertDao();
			TimeTableDao time = new TimeTableDao();

			// ログインユーザのデータ取得
			//UserInfo info = user.findUser(id);

			// 該当年月の出勤退勤データ取得
			List<Work> workList = work.findAllByMonthForId(id, year, month);

			// 該当ユーザのタイムテーブル取得
			TimeTable timeTable = time.findUser(id);

			// オブジェクト生成
			LocalDate localDate = LocalDate.of(year, month, day);

			// 日数を取得
			int lastDay = localDate.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

			// Listを生成
			List<Work> list = new ArrayList<>();
			List<String> weekList = new ArrayList<>();
			List<OffProvider.Off> offList = new ArrayList<>();

			// タイムテーブルが存在しない？
			if(timeTable.getId() == null) {
				// 仮のデータを入力しタイムテーブルを生成
				time.insert(id, 0, "", null, null, null, 5, 0.0);

				// 該当ユーザのタイムテーブル取得
				timeTable = time.findUser(id);
			}

			// 土日祝日を休日とする
			OffProvider myCompanyOff = new OffProvider(true, Week.SATURDAY, Week.SUNDAY);

			try {
				// Month型の年月を取得
				Month mon = new Month(year, month);

				// 該当月の日数分繰り返す
				for(AJD date : mon.getDays()) {
					// 曜日をリストにセット
					weekList.add(date.getWeek().getJpName());

					// 月日を代入
					OffProvider.Off off = myCompanyOff.getOff(date);

					// 休日判定をリストにセット
					offList.add(off);
				}

				// 12/29～1/3を年末年始休暇とする
				AJD from = new AJD(2000, 12, 29);
				AJD to = new AJD(2000, 1, 3);
				myCompanyOff.addOffEveryYear(from, to, new NewYear());
			} catch(AJDException e) {
				e.printStackTrace();
			}

			// レコードが存在しない？
			if(workList.isEmpty()) {
				// 最終日まで繰り返す
				for(int i = 0; i < lastDay; i++) {
					try {
						// AJD型の月日を取得
						AJD today = new AJD(year, month , i + 1);

						// 月日を代入
						OffProvider.Off off = myCompanyOff.getOff(today);

						// 現場に出向している（とみなす）？
						if(timeTable.getVisitComeTime() != null && timeTable.getVisitLeaveTime() != null) {
							// 休日？
							if(off != null) {
								// 仮のレコードを作成
								insert.insert(id, year, month, i + 1, Time.valueOf("00:00:00"),
										Time.valueOf("00:00:00"), Time.valueOf("00:00:00"),
										Time.valueOf("00:00:00"), Time.valueOf("00:00:00"),
										2, timeTable.getVisitName(), "休業日");
							// 平日？
							} else {
								// 変数宣言
								Time workTime = null;
								Time overTime = null;

								// 休憩時間を含めた勤務時間を算出
								long diffTime1 = ChronoUnit.SECONDS.between
										(LocalTime.parse(timeTable.getVisitComeTime().toString()),
												LocalTime.parse(timeTable.getVisitLeaveTime().toString()));

								// LocalTime型に変更
								LocalTime localTime1 = LocalTime.ofSecondOfDay(diffTime1);

								// 実働時間を算出
								long diffTime2 = ChronoUnit.SECONDS.between
										(localTime1, LocalTime.parse(timeTable.getVisitBrakeTime().toString()));

								// Time型に変更
								workTime = Time.valueOf(LocalTime.ofSecondOfDay(diffTime2));

								// 実働時間が8時間を超過？
								if(LocalTime.ofSecondOfDay(diffTime2).isAfter(LocalTime.of(8, 00))) {
									// 残業時間を算出
									LocalTime localTime2 = LocalTime.ofSecondOfDay(diffTime2).minusHours(8);

									// Time型に変更
									overTime = Time.valueOf(localTime2);
								// 実働時間が8時間以内？
								} else {
									// 残業時間をセット
									overTime = Time.valueOf("00:00:00");
								}

								// 仮のレコードを作成
								insert.insert(id, year, month, i + 1, timeTable.getVisitComeTime(),
										timeTable.getVisitLeaveTime(), timeTable.getVisitBrakeTime(),
										workTime, overTime, 2, timeTable.getVisitName(), "なし");
							}
						// JSD東京支店に出社している？
						} else {
							// 休日？
							if(off != null) {
								// 仮のレコードを作成
								insert.insert(id, year, month, i + 1, Time.valueOf("00:00:00"),
										Time.valueOf("00:00:00"), Time.valueOf("00:00:00"),
										Time.valueOf("00:00:00"), Time.valueOf("00:00:00"),
										1, "", "休業日");
							// 平日？
							} else {
								// 仮のレコードを作成
								insert.insert(id, year, month, i + 1, Time.valueOf("09:00:00"),
										Time.valueOf("17:40:00"), Time.valueOf("00:45:00"),
										Time.valueOf("07:55:00"), Time.valueOf("00:00:00"),
										1, "", "なし");
							}
						}
					} catch(AJDException e) {
						e.printStackTrace();
					}
				}

				// 該当年月の出勤退勤データ取得
				list = work.findAllByMonthForId(id, year, month);
			// レコードが存在する？
			} else {
				// 出勤退勤データをリストにセット
				list = workList;
			}

			// ページング設定
			int number = (lastDay + 7 - 1) / 7;

			// オフセット設定
			//list = list.subList(0, 5);

			// セッションに情報をセット
			session.setAttribute("year", year);
			session.setAttribute("month", month);
			session.setAttribute("currentpage", 1);
			session.setAttribute("number", number);
			session.setAttribute("indexNum", 0);
			session.setAttribute("lastNum", 6);
			session.setAttribute("lastDay", lastDay);
			//session.setAttribute("info", info);
			session.setAttribute("timeTable", timeTable);
			session.setAttribute("list", list);
			session.setAttribute("weekList", weekList);
			session.setAttribute("offList", offList);
			session.setAttribute("nolist", "登録されているデータがありません。");

			// 出勤退勤画面へ遷移
			response.sendRedirect("./work/home.jsp");
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
