package servlet2.work;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.TimeTable;
import entity.Work;
import test_0613f.work.UpdateDao;
import test_0613f.work.WorkDao;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/work/Update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文字化け回避
		request.setCharacterEncoding("UTF-8");

		// セッション取得の準備
		HttpSession session = request.getSession();

		// 入力値を取得（forEachで回しているため配列で受け取る）
		String work_id[] = request.getParameterValues("work_id");				// 管理ID
		String month[] = request.getParameterValues("month");					// 月
		String day[] = request.getParameterValues("day");						// 日
		String comeTime[] = request.getParameterValues("comeTime");				// 出社時刻
		String leaveTime[] = request.getParameterValues("leaveTime");			// 退社時刻
		String visit[] = request.getParameterValues("visit");					// 出社先
		String notes[] = request.getParameterValues("notes");					// 備考
		String vacation[] = request.getParameterValues("vacation");				// 休暇

		// セッションから情報を取得
		List<Work> list = (List<Work>) session.getAttribute("list");
		TimeTable timeTable = (TimeTable) session.getAttribute("timeTable");
		String id = (String) session.getAttribute("id");
		Integer year = (Integer) session.getAttribute("year");
		int indexNum = (int) session.getAttribute("indexNum");
		int lastNum = (int) session.getAttribute("lastNum");

		// 1度に表示するレコード数を制限
		List<Work> workListBefore = list.subList(indexNum, lastNum + 1);

		// 入力フォームの行数を取得
		int length = work_id.length;

		// 変数宣言
		Time ct = null;
		Time lt = null;
		Work work[] = new Work[length];
		Time brakeTime[] = new Time[length];
		Time workTime[] = new Time[length];
		Time overTime[] = new Time[length];

		// 配列の番地分繰り返す
		for(int i = 0; i < length; i++) {
			// 支店に出社した？
			if(visit[i].equals("1")) {
				// 休憩時間を取得
				brakeTime[i] = Time.valueOf("00:45:00");
			// 現場に出社した？
			} else if(visit[i].equals("2")) {
				// 休憩時間を取得
				brakeTime[i] = timeTable.getVisitBrakeTime();
			}

			// 出社時刻、退社時刻共に入力済？
			if(comeTime[i].length() > 2 &&
					leaveTime[i].length() > 2) {
				// 休憩時間を含めた勤務時間を算出
				long diffTime1 = ChronoUnit.SECONDS.between
						(LocalTime.parse(comeTime[i]), LocalTime.parse(leaveTime[i]));

				// LocalTime型に変更
				LocalTime localTime1 = LocalTime.ofSecondOfDay(diffTime1);

				// 実働時間を算出
				long diffTime2 = ChronoUnit.SECONDS.between
						(localTime1, LocalTime.parse(brakeTime[i].toString()));

				// Time型に変更
				workTime[i] = Time.valueOf(LocalTime.ofSecondOfDay(diffTime2));

				// 実働時間が8時間を超過？
				if(LocalTime.ofSecondOfDay(diffTime2).isAfter(LocalTime.of(8, 00))) {
					// 残業時間を算出
					LocalTime localTime2 = LocalTime.ofSecondOfDay(diffTime2).minusHours(8);

					// Time型に変更
					overTime[i] = Time.valueOf(localTime2);
				// 実働時間が8時間以内？
				} else {
					// 残業時間をセット
					overTime[i] = Time.valueOf("00:00:00");
				}

				// Time型に変換
				ct = Time.valueOf(comeTime[i]);
				lt = Time.valueOf(leaveTime[i]);

			// 出社時刻か退社時刻が未入力？
			} else {
				// 出社時刻は入力済？
				if(comeTime[i].length() > 2) {
					// Time型に変換
					ct = Time.valueOf(comeTime[i]);
				}

				// 退社時刻は入力済？
				if(leaveTime[i].length() > 2) {
					// Time型に変換
					lt = Time.valueOf(leaveTime[i]);
				}

				// nullをセット
				workTime[i] = null;
				overTime[i] = null;
			}

			// インスタンス生成
			work[i] = new Work(Integer.valueOf(work_id[i]), id, year,
					Integer.valueOf(month[i]), Integer.valueOf(day[i]),
					ct, lt, brakeTime[i], workTime[i], overTime[i],
					Integer.valueOf(visit[i]), notes[i], vacation[i]);
		}

		// リストに変換
		List<Work> workListAfter = Arrays.asList(work);

		// 変更前と変更後を比較して変更点のあったレコードのみ取得
		// 生成：それぞれのListに対応するListのstreamを生成
		// 中間操作：workに対し、対称のworkと比較し要素を変更したか確認
		// 終端操作：streamの結果をListに変換
		// 比較用
		List<Work> workListUp = workListBefore.stream()
				.filter(t1 -> workListAfter.stream()
						.allMatch(t2 -> t1.getMonth() != t2.getMonth() ||
								t1.getDay() != t2.getDay() ||
								t1.getComeTime().compareTo(t2.getComeTime()) != 0 ||
								t1.getLeaveTime().compareTo(t2.getLeaveTime()) != 0 ||
								t1.getBrakeTime().compareTo(t2.getBrakeTime()) != 0 ||
								t1.getWorkTime().compareTo(t2.getWorkTime()) != 0 ||
								t1.getOverTime().compareTo(t2.getOverTime()) != 0 ||
								!t1.getVisit().equals(t2.getVisit()) ||
								!t1.getNotes().equals(t2.getNotes()) ||
								!t1.getVacation().equals(t2.getVacation())))
				.collect(Collectors.toList());

		// 更新用
		List<Work> workListComp = workListAfter.stream()
				.filter(t2 -> workListBefore.stream()
						.allMatch(t1 -> t2.getMonth() != t1.getMonth() ||
								t2.getDay() != t1.getDay() ||
								t2.getComeTime() != t1.getComeTime() ||
								t2.getLeaveTime() != t1.getLeaveTime() ||
								t2.getBrakeTime() != t1.getBrakeTime() ||
								t2.getWorkTime() != t1.getWorkTime() ||
								t2.getOverTime() != t1.getOverTime() ||
								!t2.getVisit().equals(t1.getVisit()) ||
								!t2.getNotes().equals(t1.getNotes()) ||
								!t2.getVacation().equals(t1.getVacation())))
				.collect(Collectors.toList());

		// リストのサイズを取得
		int size = workListComp.size();

		// 変更なし？
		if(size == 0) {
			// home.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/work/home.jsp");
			dispatch.forward(request, response);
		// 変更あり？
		} else {
			// DAOの宣言
			UpdateDao updateDao = new UpdateDao();
			WorkDao workDao = new WorkDao();

			// 変更分だけ更新
			for(int i = 0; i < size; i++) {
				updateDao.update(workListComp.get(i).getMonth(), workListComp.get(i).getDay(),
						workListComp.get(i).getComeTime(), workListComp.get(i).getLeaveTime(),
						workListComp.get(i).getBrakeTime(), workListComp.get(i).getWorkTime(),
						workListComp.get(i).getOverTime(), workListComp.get(i).getVisit(),
						workListComp.get(i).getNotes(), workListComp.get(i).getVacation(),
						workListComp.get(i).getWork_id());
			}

			// 変更前データの該当月を取得
			Integer mon = workListBefore.get(0).getMonth();

			// 変更後のリスト取得
			List<Work> workList = workDao.findAllByMonthForId(id, year, mon);

			// リクエストに情報をセット
			request.setAttribute("workListUp", workListUp);
			request.setAttribute("workListComp", workListComp);

			// セッションに情報をセット
			session.setAttribute("list", workList);

			// updateResult.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/work/updateResult.jsp");
			dispatch.forward(request, response);
		}
	}
}
