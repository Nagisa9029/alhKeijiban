package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Comment;
import service.CommentService;

@WebServlet(urlPatterns = { "/DeleteComment" })
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Comment comment = new Comment();
		comment.setId(Integer.parseInt(request.getParameter("id")));
		new CommentService().delete(comment);


		response.sendRedirect("./");

	}
}