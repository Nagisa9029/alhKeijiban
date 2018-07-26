package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Post;
import service.PostService;

@WebServlet(urlPatterns = { "/delete" })
public class DeletePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Post post = new Post();
		post.setId(Integer.parseInt(request.getParameter("id")));
		new PostService().delete(post);

		response.sendRedirect("./");

	}
}