package servlet2;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.TotalM;
import entity.UserInfo;
import test_0613f.LoginUser;
import test_0613f.TotalMDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		Calendar cal = Calendar.getInstance();
		int m = cal.get(Calendar.MONTH) + 1;

		String month = String.valueOf(m);

		LoginUser lu = new LoginUser();
		UserInfo ui = lu.findUser(id);

		boolean isLogin = (ui != null && id.equals(ui.getId()) &&
				pass.equals(ui.getPass()));
		HttpSession session = request.getSession();
		session.setAttribute("isLogin", isLogin);

		if (isLogin) {

			session.setAttribute("id", ui.getId());
			session.setAttribute("name", ui.getName());

			TotalMDao tmd = new TotalMDao();


			List<TotalM> list = tmd.findAllByMonth(id, month);

			session.setAttribute("list", list);
			session.setAttribute("nolist", "登録されているデータがありません。");

			RequestDispatcher dispatch = request.getRequestDispatcher("/home.jsp");
			dispatch.forward(request, response);

		} else {
			request.setAttribute("error", "IDかパスワードが間違っています。\n再入力してください。");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

	}

}
