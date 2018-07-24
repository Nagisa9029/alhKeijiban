package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IndexUser;
import service.UserService;


@WebServlet(urlPatterns = { "/IndexUsers" })
public class IndexUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<IndexUser> users = new UserService().getUser();
		request.setAttribute("users", users);

		request.getRequestDispatcher("/IndexUsers.jsp").forward(request, response);
	}

}