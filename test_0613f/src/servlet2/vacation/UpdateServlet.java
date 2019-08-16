package servlet2.vacation;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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

import entity.Vacation;
import test_0613f.vacation.UpdateDao;
import test_0613f.vacation.VacationDao;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/vacation/Update")
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
		String vacation_id[] = request.getParameterValues("vacation_id");		// 管理ID
		String fromMonth[] = request.getParameterValues("fromMonth");			// 開始月
		String fromDay[] = request.getParameterValues("fromDay");				// 開始日
		String toMonth[] = request.getParameterValues("toMonth");				// 終了月
		String toDay[] = request.getParameterValues("toDay");					// 終了日
		String division[] = request.getParameterValues("division");				// 区分
		String reason[] = request.getParameterValues("reason");					// 事由

		// セッションから情報を取得
		List<Vacation> vacationListBefore = (List<Vacation>) session.getAttribute("list");
		String id = (String) session.getAttribute("id");
		Integer year = (Integer) session.getAttribute("year");

		// 入力フォームの行数を取得
		int length = vacation_id.length;

		// Integer型の配列を生成
		Integer totalDay[] = new Integer[length];

		// 変数宣言
		Integer total = 0;

		// 入力フォームの行数分合計日数の計算
		for(int i = 0; i < length; i++) {
			// 同月？
			if(Integer.valueOf(toDay[i]) - Integer.valueOf(fromDay[i]) >= 0) {
				total = Integer.valueOf(toDay[i]) - Integer.valueOf(fromDay[i]) + 1;
			// 月跨ぎ？
			} else {
				// オブジェクト生成
				LocalDate localDate = LocalDate.of(year, Integer.valueOf(fromMonth[i]), Integer.valueOf(fromDay[i]));

				// 日数を取得
				int lastDay = localDate.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

				// 月末は28日？
				if(lastDay == 28) {
					total = 28 + Integer.valueOf(toDay[i]) - Integer.valueOf(fromDay[i]) + 1;
				// 月末は29日？
				} else if(lastDay == 29) {
					total = 29 + Integer.valueOf(toDay[i]) - Integer.valueOf(fromDay[i]) + 1;
				// 月末は30日？
				} else if(lastDay == 30) {
					total = 30 + Integer.valueOf(toDay[i]) - Integer.valueOf(fromDay[i]) + 1;
				// 月末は31日？
				} else {
					total = 31 + Integer.valueOf(toDay[i]) - Integer.valueOf(fromDay[i]) + 1;
				}
			}

			// 取得日数を代入
			totalDay[i] = new Integer(total);
		}

		// Vacation型の配列を生成
		Vacation vacation[] = new Vacation[length];

		// 配列の番地ごとに入力値を代入
		for(int i = 0; i < length; i++) {
			vacation[i] = new Vacation(Integer.valueOf(vacation_id[i]), id,
					year, Integer.valueOf(fromMonth[i]), Integer.valueOf(fromDay[i]),
					Integer.valueOf(toMonth[i]), Integer.valueOf(toDay[i]),
					totalDay[i], division[i], reason[i]);
		}

		// リストに変換
		List<Vacation> vacationListAfter = Arrays.asList(vacation);

		// 変更前と変更後を比較して変更点のあったレコードのみ取得
		// 生成：それぞれのListに対応するListのstreamを生成
		// 中間操作：vacationに対し、対称のvacationと比較し要素を変更したか確認
		// 終端操作：streamの結果をListに変換
		// 比較用
		List<Vacation> vacationListUp = vacationListBefore.stream()
				.filter(t1 -> vacationListAfter.stream()
						.allMatch(t2 -> t1.getFromMonth() != t2.getFromMonth() ||
								t1.getFromDay() != t2.getFromDay() ||
								t1.getToMonth() != t2.getToMonth() ||
								t1.getToDay() != t2.getToDay() ||
								t1.getTotalDay() != t2.getTotalDay() ||
								!t1.getDivision().equals(t2.getDivision()) ||
								!t1.getReason().equals(t2.getReason())))
				.collect(Collectors.toList());

		// 更新用
		List<Vacation> vacationListComp = vacationListAfter.stream()
				.filter(t2 -> vacationListBefore.stream()
						.allMatch(t1 -> t2.getFromMonth() != t1.getFromMonth() ||
								t2.getFromDay() != t1.getFromDay() ||
								t2.getToMonth() != t1.getToMonth() ||
								t2.getToDay() != t1.getToDay() ||
								t2.getTotalDay() != t1.getTotalDay() ||
								!t2.getDivision().equals(t1.getDivision()) ||
								!t2.getReason().equals(t1.getReason())))
				.collect(Collectors.toList());

		// リストのサイズを取得
		int size = vacationListComp.size();

		// 変更なし？
		if(size == 0) {
			// home.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/vacation/home.jsp");
			dispatch.forward(request, response);
			return;
		}

		// DAOの宣言
		UpdateDao updateDao = new UpdateDao();
		VacationDao vacationDao = new VacationDao();

		// 変更分だけ更新
		for(int i = 0; i < size; i++) {
			updateDao.update(vacationListComp.get(i).getFromMonth(), vacationListComp.get(i).getFromDay(),
					vacationListComp.get(i).getToMonth(), vacationListComp.get(i).getToDay(),
					vacationListComp.get(i).getTotalDay(), vacationListComp.get(i).getDivision(),
					vacationListComp.get(i).getReason(), vacationListComp.get(i).getVacation_id());
		}

		// 変更後のリスト取得
		List<Vacation> vacationList = vacationDao.findAllByMonthForId(id, year, 0);

		// リクエストに情報をセット
		request.setAttribute("vacationListUp", vacationListUp);
		request.setAttribute("vacationListComp", vacationListComp);

		// セッションに情報をセット
		session.setAttribute("list", vacationList);

		// modifyResult.jspに遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("/vacation/modifyResult.jsp");
		dispatch.forward(request, response);
	}

}
