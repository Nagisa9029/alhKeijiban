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

import org.apache.commons.lang.StringUtils;

import beans.Comment;
import beans.User;
import service.CommentService;

@WebServlet(urlPatterns = { "/NewComment" })
public class NewCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		if (isValid(request, messages) == true) {

			User user = (User) session.getAttribute("loginUser");

			Comment comment = new Comment();
			comment.setText(request.getParameter("text"));
			comment.setUserId(user.getId());
			comment.setPostId(Integer.parseInt(request.getParameter("post")));

			new CommentService().register(comment);

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("top.jsp");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String text = request.getParameter("text");

		if (StringUtils.isBlank(text) == true) {
			messages.add("コメントを入力してください");
		}
		if (500 < text.length()) {
			messages.add("コメントは500文字以下で入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
