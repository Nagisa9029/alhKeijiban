package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import service.LoginService;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		if (isValid(request, messages) == true) {
			String account = request.getParameter("account");
			String password = request.getParameter("password");

			LoginService loginService = new LoginService();
			User user = loginService.login(account, password);

			session.setAttribute("loginUser", user);
			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			User user = new User();

			user.setAccount(request.getParameter("account"));
			request.setAttribute("user", user);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		LoginService loginService = new LoginService();
		User user = loginService.login(account, password);

		if (user == null || user.getIsStopped() == 0) {
			messages.add("ログインに失敗しました。");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}