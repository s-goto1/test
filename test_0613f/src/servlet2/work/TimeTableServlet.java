package servlet2.work;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.TimeTable;
import test_0613f.work.TimeTableDao;

/**
 * Servlet implementation class TimeTableServlet
 */
@WebServlet("/TimeTable")
public class TimeTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimeTableServlet() {
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

		// セッション取得の準備
		HttpSession session = request.getSession();

		// 入力値を取得
		String visitName = request.getParameter("visitName").replaceFirst("^[\\h]+", "").replaceFirst("[\\h]+$", "");
		String visitComeTime = request.getParameter("visitComeTime");
		String visitLeaveTime = request.getParameter("visitLeaveTime");
		String visitBrakeTime = request.getParameter("visitBrakeTime");
		String roundTime = request.getParameter("roundTime");
		String companyNum = request.getParameter("companyNum");

		// セッションから情報を取得
		TimeTable timeTable = (TimeTable) session.getAttribute("timeTable");

		// DAOの宣言
		TimeTableDao dao = new TimeTableDao();

		// 変数宣言
		Time vct = null;
		Time vlt = null;
		Time vbt = null;
		Integer cn = 0;
		Integer timeTableId = timeTable.getTimeTableId();

		// 出社時刻が入力済？
		if(visitComeTime.length() > 2) {
			// Time型に変換
			vct = Time.valueOf(visitComeTime);
		}

		// 退社時刻が入力済？
		if(visitLeaveTime.length() > 2) {
			// Time型に変換
			vlt = Time.valueOf(visitLeaveTime);
		}

		// 休憩時間が入力済？
		if(visitBrakeTime.length() > 2) {
			// Time型に変換
			vbt = Time.valueOf(visitBrakeTime);
		}

		// 社員番号が入力済？
		if(companyNum.length() > 0) {
			// Integer型に変換
			cn = Integer.parseInt(companyNum);
		}

		// 現場企業名、時刻系全て入力済か未入力？
		if((visitName.length() > 0 && vct != null && vlt != null && vbt != null) ||
				(visitName.length() == 0 && vct == null && vlt == null && vbt == null)) {
			// タイムテーブル更新
			dao.updateTable(cn, visitName, vct, vlt, vbt, Integer.parseInt(roundTime), timeTableId);

			// セッションに情報をセット
			session.setAttribute("timeTable", timeTable);

			// timeTableResult.jspへ遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/work/timeTableResult.jsp");
			dispatch.forward(request, response);
		// 現場企業名が記入されているのに時刻系が未入力？
		} else if(visitName.length() > 0 && (vct == null || vlt == null || vbt == null)) {
			// リクエストに情報をセット
			request.setAttribute("error", "時刻設定が空欄になっている箇所があります。全て入力して下さい。");

			// timeTable.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/work/timeTable.jsp");
			dispatch.forward(request, response);
		// 現場企業名が未入力かつ時刻系も未入力な箇所がある？
		} else if(visitName.length() == 0 && (vct == null || vlt == null || vbt == null)) {
			// リクエストに情報をセット
			request.setAttribute("error", "現場企業名と時刻設定が空欄になっている箇所があります。全て入力して下さい。");

			// timeTable.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/work/timeTable.jsp");
			dispatch.forward(request, response);
		// 時刻系が記入されているのに現場企業名が未入力？
		} else if(visitName.length() == 0 && (vct != null || vlt != null || vbt != null)) {
			// リクエストに情報をセット
			request.setAttribute("error", "現場企業名が空欄になっています。入力して下さい。");

			// timeTable.jspに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("/work/timeTable.jsp");
			dispatch.forward(request, response);
		}
	}
}
