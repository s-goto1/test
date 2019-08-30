package servlet2.work;

import java.io.IOException;
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

import entity.Work;
import test_0613f.work.WorkDao;

/**
 * Servlet implementation class PagingServlet
 */
@WebServlet("/work/Paging")
public class PagingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け回避
		request.setCharacterEncoding("UTF-8");

		// セッション情報取得の準備
		HttpSession session = request.getSession();

		// リクエストから情報を取得
		String page = request.getParameter("page");

		// 型変換
		int currentPage = Integer.valueOf(page);
		int offset = Integer.valueOf(page) * 7 - 7;

		// セッションから情報を取得
		//String id = (String) session.getAttribute("id");
		Integer year = (Integer) session.getAttribute("year");
		Integer month = (Integer) session.getAttribute("month");
		Integer auth = (Integer) session.getAttribute("auth");
		int lastDay = (int) session.getAttribute("lastDay");
		List<Work> list = (List<Work>) session.getAttribute("list");
		//List<Integer> dayList = (List<Integer>) session.getAttribute("dayList");

		// DAOの宣言
		WorkDao dao = new WorkDao();

		// 権限が管理者？
		if(auth == 1) {
			// セッションから情報を取得
			List<String> idList = (List<String>) session.getAttribute("idList");
			List<String> nameList = (List<String>) session.getAttribute("nameList");

			// リストのサイズを取得
			int size = idList.size();

			// ページ数を取得
			int number = size;

			// String型のListの宣言
			List<String> idListLimit = new ArrayList<>();

			// 1度に表示するユーザ数を制限
			idListLimit = idList.subList(currentPage, currentPage + 3);

			// 今月分の規定数の社員の出勤退勤データを取得
			Map<String, List<Work>> map = idListLimit.stream()
					.collect(Collectors.toMap(
							s -> s,
							s -> dao.findAllByMonthForIdFromAdmin(s, year, month)));

			// セッションに情報をセット
			session.setAttribute("currentpage", 1);
			session.setAttribute("size", size);
			session.setAttribute("number", number);
			session.setAttribute("map", map);
			session.setAttribute("idList", idList);
			session.setAttribute("nameList", nameList);

			// homeAdmin.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/work/homeAdmin.jsp");
			dispatch.forward(request, response);
		// 権限が担当者？
		} else {
//			// 該当年月の出勤退勤データ取得
//			List<Work> workList = dao.findAllByMonthForId(id, year, month);
//
//			// Work型のListを生成
//			List<Work> list = new ArrayList<>();
//
//			// 最終日まで繰り返す
//			for(int i = 0; i < lastDay; i++) {
//				// 該当日のレコードが存在する？
//				if(dayList.contains(i + 1)) {
//					// 該当日のインデックスを取得
//					Integer currentDay = dayList.indexOf(i + 1);
//
//					// 該当日のレコードをリストに追加
//					list.add(workList.stream()
//							.filter(w -> w.getDay() == currentDay)
//							.collect(Collectors.toList())
//							.get(0));
//				// 該当日のレコードが存在しない？
//				} else {
//					// nullをリストに追加
//					list.add(null);
//				}
//			}

			// ページング設定
			int number = (lastDay + 7 - 1) / 7;

			// オフセット設定
			//list = list.subList(offset, offset + 5);

			// リストのサイズを取得
			int size = list.size();

			// リストの開始インデックス設定
			int indexNum = offset;

			// 変数宣言
			int lastNum = 0;

			// 残りレコードは7件以上？
			if(offset + 6 <= size) {
				// 1度に表示する件数を制限
				lastNum = offset + 6;
			// 残りレコードは7件未満？
			} else {
				// 1度に表示する件数を制限
				lastNum = size;
			}

			// セッションに必要な情報をセット
			session.setAttribute("currentpage", currentPage);
			session.setAttribute("year", year);
			session.setAttribute("month", month);
			session.setAttribute("number", number);
			session.setAttribute("indexNum", indexNum);
			session.setAttribute("lastNum", lastNum);
			session.setAttribute("list", list);
			session.setAttribute("nolist", "登録されているデータがありません。");

			// home.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/work/home.jsp");
			dispatch.forward(request, response);
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
