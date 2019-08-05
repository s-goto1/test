package servlet2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		Integer auth = (Integer) session.getAttribute("masterAuth");

		// 権限が管理者？
		if(auth == 1) {
			// 検索画面へ遷移
			response.sendRedirect("./vacation/search.jsp");
		// 権限が担当者？
		} else {
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
