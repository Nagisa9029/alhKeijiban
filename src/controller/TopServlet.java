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

		String dateStr = "2018-01-01";
		String dateEnd = "2020-12-31";
		String category = null;

		if(request.getParameter("dateStr") != null && request.getParameter("dateEnd") != null){
			if (request.getParameter("dateStr").length() != 0) {
				dateStr = request.getParameter("dateStr");
			}
			if (request.getParameter("dateEnd").length() != 0) {
				dateEnd = request.getParameter("dateEnd");
			}
		}
		if (request.getParameter("category") != null) {
			category = request.getParameter("category");
		}


		List<UserPost> posts = new PostService().getPost(dateStr, dateEnd, category);
		request.setAttribute("posts",  posts);

		List<UserComment> comments = new CommentService().getComment();
		request.setAttribute("comments",  comments);

		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}
}

