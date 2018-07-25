package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserComment;
import beans.UserPost;
import service.CommentService;
import service.PostService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {


		List<UserPost> posts = new PostService().getPost();
		request.setAttribute("posts",  posts);
		System.out.println(posts);
		List<UserComment> comments = new CommentService().getComment();
		request.setAttribute("comments",  comments);
		System.out.println(comments);

		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}
}

