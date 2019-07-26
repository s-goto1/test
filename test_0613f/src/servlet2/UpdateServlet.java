package servlet2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.ListUtils;

import entity.TotalM;
import test_0613f.TotalMDao;
import test_0613f.UpdateDao;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/Update")
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
		String month[] = request.getParameterValues("month");				// 月
		String date[] = request.getParameterValues("date");					// 日
		String depature[] = request.getParameterValues("depature");			// 出発駅
		String destination[] = request.getParameterValues("destination");	// 到着駅
		String money[] = request.getParameterValues("money");				// 金額
		String totalM_id[] = request.getParameterValues("totalM_id");		// 管理ID
		String division[] = request.getParameterValues("division");


		// セッションの取得
		List<TotalM> totalMListBefore = (List<TotalM>) session.getAttribute("list");
		String id = (String) session.getAttribute("id");




		// 入力フォームの行数を取得
		int idLength = totalM_id.length;

		// TotalM型の配列を生成
		TotalM totalM[] = new TotalM[idLength];

		// 配列の番地ごとに入力値を代入
		for(int i = 0; i < idLength; i++) {
			totalM[i] = new TotalM(null, Integer.valueOf(totalM_id[i]),
					Integer.valueOf(money[i]), Integer.valueOf(month[i]),  Integer.valueOf(date[i]), depature[i],
					destination[i], division[i]);
		}

		// リストに変換
		List<TotalM> totalMListAfter = Arrays.asList(totalM);

		// 非重複リストを取得する準備
		List<TotalM> totalMList = new ArrayList<>();

		// 変更前と変更後を比較して変更点のあったレコードのみ取得
		ListUtils.subtract(totalMListBefore, totalMListAfter)
				.stream()
				.forEach(t -> totalMList.add(t));

		// リストのサイズを取得
		int listLength = totalMList.size();

		// DAOの宣言
		UpdateDao updateDao = new UpdateDao();
		TotalMDao totalMDao = new TotalMDao();

		// 変更分だけ更新
		for(int i = 0; i < listLength; i++) {
			updateDao.update(Integer.valueOf(month[i]), Integer.valueOf(date[i]), depature[i], destination[i], division[i],
					Integer.valueOf(money[i]), Integer.valueOf(totalM_id[i]));
		}

		// 現在日時を取得
		Calendar cal = Calendar.getInstance();

		// 現在月を取得
		int mon = cal.get(Calendar.MONTH) + 1;

		// 変更後のリスト取得
		List<TotalM> totalMListUpdate = totalMDao.findAllByMonth(id,mon);

		// リストのレコード数取得
		int size = totalMListUpdate.size();

		// String型のListオブジェクトを生成
		List<String> divisionList = new ArrayList<>();

		// リストのレコード分繰り返す
		for(int i = 0; i < size; i++) {
			// 区分を取得
			String divi = totalMListUpdate.get(i).getDivision();
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

		// セッションに情報をセット
		session.setAttribute("list", totalMListUpdate);
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

		// home.jspに遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("/home.jsp");
		dispatch.forward(request, response);
	}

}
