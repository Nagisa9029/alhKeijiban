package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IndexUser;
import beans.UserComment;
import beans.UserPost;
import service.CommentService;
import service.PostService;
import service.UserService;


@WebServlet(urlPatterns = { "/users" })
public class IndexUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String dateStr = "2018-01-01";
		String dateEnd = "2020-12-31";
		String cate= null;

		List<IndexUser> users = new UserService().getUser();
		request.setAttribute("users", users);

		List<UserPost> posts = new PostService().getPost(dateStr, dateEnd, cate);
		request.setAttribute("posts", posts);

		List<UserComment> comments = new CommentService().getComment();
		request.setAttribute("comments", comments);

		request.getRequestDispatcher("IndexUsers.jsp").forward(request, response);
	}

}