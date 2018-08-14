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
import beans.UserComment;
import beans.UserPost;
import service.CommentService;
import service.PostService;

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
			comment.setBranchName(user.getBranchName());
			comment.setPositionName(user.getPositionName());
			comment.setPostId(Integer.parseInt(request.getParameter("post")));

			new CommentService().register(comment);

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			Comment comment = new Comment();
			comment.setText(request.getParameter("text"));
			comment.setPostId(Integer.parseInt(request.getParameter("post")));
			String dateStr = null;
			String dateEnd = null;
			String cate = null;
			String bran = null;

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

			List<UserPost> posts = new PostService().getPost(dateStr, dateEnd, cate, bran);
			request.setAttribute("posts", posts);

			List<UserComment> comments = new CommentService().getComment();
			request.setAttribute("comments", comments);

			request.getRequestDispatcher("top.jsp").forward(request, response);
			//response.sendRedirect("top.jsp");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String text = request.getParameter("text");
		String[] str = text.split("\r\n");
		int sub = 0;
		for(int i = 0; i < str.length; i ++){
			sub += str[i].length();
		}


		if (StringUtils.isBlank(text) == true) {
			messages.add("コメントを入力してください");
		}
		if (500 < sub) {
			messages.add("コメントは500文字以下で入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
