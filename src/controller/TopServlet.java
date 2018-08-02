package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

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

		String dateStr = null;
		String dateEnd = null;
		String cate= null;

		if (StringUtils.isBlank(request.getParameter("dateStr")) == true) {
			dateStr = "2018-01-01";
			dateEnd = "2020-12-31";
			//cate = null;
		}
		if (StringUtils.isBlank(request.getParameter("dateStr")) != true) {
			dateStr = request.getParameter("dateStr");
			request.setAttribute("dateStr", dateStr);
		}
		if (StringUtils.isBlank(request.getParameter("dateEnd")) != true){
			dateEnd = request.getParameter("dateEnd");
			request.setAttribute("dateEnd", dateEnd);
		}
		if (request.getParameter("cate") != null) {
			cate = request.getParameter("cate");
			request.setAttribute("cate", cate);
		}


		List<UserPost> posts = new PostService().getPost(dateStr, dateEnd, cate);
		request.setAttribute("posts", posts);

		List<UserComment> comments = new CommentService().getComment();
		request.setAttribute("comments", comments);

		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}
}

