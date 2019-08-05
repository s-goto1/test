package servlet2;

import java.io.IOException;
import java.util.ArrayList;
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

import entity.TotalM;
import test_0613f.TotalMDao;
import test_0613f.UpdateDao;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/business/Update")
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
		String totalM_id[] = request.getParameterValues("totalM_id");			// 管理ID
		String month[] = request.getParameterValues("month");					// 月
		String day[] = request.getParameterValues("day");						// 日
		String depature[] = request.getParameterValues("depature");				// 出発駅
		String destination[] = request.getParameterValues("destination");		// 到着駅
		String division[] = request.getParameterValues("division");				// 区分
		String transportation[] = request.getParameterValues("transportation");	// 交通機関
		String place[] = request.getParameterValues("place");					// 訪問先
		String money[] = request.getParameterValues("money");					// 金額
		String purpose[] = request.getParameterValues("purpose");				// 用件

		// セッションから情報を取得
		List<TotalM> totalMListBefore = (List<TotalM>) session.getAttribute("list");
		String id = (String) session.getAttribute("id");
		int year = (int) session.getAttribute("year");

		// 入力フォームの行数を取得
		int length = totalM_id.length;

		// TotalM型の配列を生成
		TotalM totalM[] = new TotalM[length];

		// 配列の番地ごとに入力値を代入
		for(int i = 0; i < length; i++) {
			totalM[i] = new TotalM(id, Integer.valueOf(totalM_id[i]),
					Integer.valueOf(money[i]), year, Integer.valueOf(month[i]),
					Integer.valueOf(day[i]), transportation[i], depature[i],
					destination[i], division[i], place[i], purpose[i]);
		}

		// リストに変換
		List<TotalM> totalMListAfter = Arrays.asList(totalM);

//		// 非重複リストを取得する準備
//		HashSet<TotalM> hashSetBefore = new HashSet<>(totalMListBefore);
//		HashSet<TotalM> hashSetAfter = new HashSet<>(totalMListAfter);
//
//		// 変更前と変更後を比較して変更点のあったレコードのみ取得
//		// 比較用
//		List<TotalM> totalMListUp = totalMListBefore.stream()
//				.filter(t -> {
//					return (! hashSetAfter.contains(t));
//				})
//				.collect(Collectors.toList());
//
//		// 更新用
//		List<TotalM> totalMListComp = totalMListAfter.stream()
//				.filter(t -> {
//					return (! hashSetBefore.contains(t));
//				})
//				.collect(Collectors.toList());

		// 変更前と変更後を比較して変更点のあったレコードのみ取得
		// 生成：それぞれのListに対応するListのstreamを生成
		// 中間操作：totalMに対し、対称のtotalMと比較し要素を変更したか確認
		// 終端操作：streamの結果をListに変換
		// 比較用
		List<TotalM> totalMListUp = totalMListBefore.stream()
				.filter(t1 -> totalMListAfter.stream()
						.allMatch(t2 -> t1.getMoney() != t2.getMoney() ||
								t1.getMonth() != t2.getMonth() ||
								t1.getDay() != t2.getDay() ||
								!t1.getTransportation().equals(t2.getTransportation()) ||
								!t1.getDepature().equals(t2.getDepature()) ||
								!t1.getDestination().equals(t2.getDestination()) ||
								!t1.getDivision().equals(t2.getDivision()) ||
								!t1.getPlace().equals(t2.getPlace()) ||
								!t1.getPurpose().equals(t2.getPurpose())))
				.collect(Collectors.toList());

		// 更新用
		List<TotalM> totalMListComp = totalMListAfter.stream()
				.filter(t2 -> totalMListBefore.stream()
						.allMatch(t1 -> t2.getMoney() != t1.getMoney() ||
								t2.getMonth() != t1.getMonth() ||
								t2.getDay() != t1.getDay() ||
								!t2.getTransportation().equals(t1.getTransportation()) ||
								!t2.getDepature().equals(t1.getDepature()) ||
								!t2.getDestination().equals(t1.getDestination()) ||
								!t2.getDivision().equals(t1.getDivision()) ||
								!t2.getPlace().equals(t1.getPlace()) ||
								!t2.getPurpose().equals(t1.getPurpose())))
				.collect(Collectors.toList());

//		// 非重複リストを取得する準備
//		List<TotalM> totalMListUp = new ArrayList<>();
//		List<TotalM> totalMListComp = new ArrayList<>();
//
//		// 変更前と変更後を比較して変更点のあったレコードのみ取得
//		// 比較用
//		ListUtils.subtract(totalMListBefore, totalMListAfter)
//				.stream()
//				.forEach(t -> totalMListUp.add(t));
//		// 更新用
//		ListUtils.subtract(totalMListAfter, totalMListBefore)
//				.stream()
//				.forEach(t -> totalMListComp.add(t));

		// リストのサイズを取得
		int size = totalMListComp.size();

		// 変更なし？
		if(size == 0) {
			// home.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/business/home.jsp");
			dispatch.forward(request, response);
			return;
		}

		// DAOの宣言
		UpdateDao updateDao = new UpdateDao();
		TotalMDao totalMDao = new TotalMDao();

		// 変更分だけ更新
		for(int i = 0; i < size; i++) {
			updateDao.update(totalMListComp.get(i).getMonth(), totalMListComp.get(i).getDay(),
					totalMListComp.get(i).getTransportation(), totalMListComp.get(i).getDepature(),
					totalMListComp.get(i).getDestination(), totalMListComp.get(i).getDivision(),
					totalMListComp.get(i).getMoney(), totalMListComp.get(i).getPlace(),
					totalMListComp.get(i).getPurpose(), totalMListComp.get(i).getTotalM_id());
		}

		// 変更前データの該当月を取得
		int mon = totalMListBefore.get(0).getMonth();

		// 変更後のリスト取得
		List<TotalM> totalMList = totalMDao.findAllByMonth(id, year, mon);

		// リストのレコード数取得
		int upSize = totalMList.size();

		// String型のListオブジェクトを生成
		List<String> divisionList = new ArrayList<>();

		// リストのレコード分繰り返す
		for(int i = 0; i < upSize; i++) {
			// 区分を取得
			String divi = totalMList.get(i).getDivision();

			// 区分が片道？
			if(divi.equals("片道")) {
				// 往復をセット
				divi = "往復";
				divisionList.add(divi);
			// 区分が往復？
			} else {
				// 片道をセット
				divi = "片道";
				divisionList.add(divi);
			}
		}

		// リクエストに情報をセット
		request.setAttribute("totalMListUp", totalMListUp);
		request.setAttribute("totalMListComp", totalMListComp);

		// セッションに情報をセット
		session.setAttribute("list", totalMList);
		session.setAttribute("divisionList", divisionList);

		// リストに変換するための二次元配列を生成
		//String updateTable [][]= new String[idLength][5];


		// 配列の番地ごとに入力値を代入
		//for(int i = 0; i < idLength; i++) {
		//	updateTable[i][0] = date[i];
		//	updateTable[i][1] = depature[i];
		//	updateTable[i][2] = destination[i];
		//	updateTable[i][3] = money[i];
		//	updateTable[i][4] = totalM_id[i];
		//}

		//

//		List<TotalM> totalM =
//		Collections.addAll(uplist, date);

//		System.out.println(uplist.get(0));

		//		int totalM_id = Integer.parseInt(totalM_id1);
		//		int money = Integer.parseInt(money1);

		//		UpdateDao ud = new UpdateDao();
		//ud.update(date, depature, destination, money, totalM_id);

		// modifyResult.jspに遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("/business/modifyResult.jsp");
		dispatch.forward(request, response);
	}

}
