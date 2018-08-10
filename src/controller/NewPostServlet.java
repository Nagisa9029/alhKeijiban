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

import beans.Post;
import beans.User;
import service.PostService;

@WebServlet(urlPatterns = { "/newpost" })
public class NewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("NewPost.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		Post post = getPost(request);

		if (isValid(request, messages) == true) {
			new PostService().register(post);
			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			request.setAttribute("post", post);
			//response.sendRedirect("newpost");
			request.getRequestDispatcher("NewPost.jsp").forward(request, response);
		}
	}

	private Post getPost(HttpServletRequest request)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");

		Post post = new Post();
		post.setTitle(request.getParameter("title"));
		post.setText(request.getParameter("text"));
		post.setCategory(request.getParameter("category"));
		post.setUserId(user.getId());
		return post;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String category = request.getParameter("category");
		String[] str = text.split("\r\n");
		int sub = 0;
		for(int i = 0; i < str.length; i ++){
			sub += str[i].length();
		}

		if (StringUtils.isBlank(title) == true) {
			messages.add("タイトルを入力してください");
		}
		if (30 < title.length()) {
			messages.add("タイトルは30文字以下で入力してください");
		}
		if (StringUtils.isBlank(category) == true) {
			messages.add("カテゴリーを入力してください");
		}
		if (10 < category.length()) {
			messages.add("カテゴリーは10文字以下で入力してください");
		}
		if (StringUtils.isBlank(text) == true) {
			messages.add("本文を入力してください");
		}
		if (1000 < sub) {
			messages.add("本文は1000文字以下で入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
