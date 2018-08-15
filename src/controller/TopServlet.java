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

import beans.Branch;
import beans.Comment;
import beans.User;
import beans.UserComment;
import beans.UserPost;
import service.BranchService;
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
		String cate = null;
		String bran = null;

		List<UserPost> branchNumbers = new PostService().getPost(dateStr, dateEnd, cate, bran);
		request.setAttribute("branchNumbers", branchNumbers);

		if (StringUtils.isBlank(request.getParameter("dateStr")) != true) {
			dateStr = request.getParameter("dateStr");
			request.setAttribute("dateStr", dateStr);
		}
		if (StringUtils.isBlank(request.getParameter("dateEnd")) != true){
			dateEnd = request.getParameter("dateEnd");
			request.setAttribute("dateEnd", dateEnd);
		}
		if (StringUtils.isBlank(request.getParameter("cate")) != true){
			cate = request.getParameter("cate");
			request.setAttribute("cate", cate);
		}
		if (StringUtils.isBlank(request.getParameter("bran")) != true){
			bran = request.getParameter("bran");
			request.setAttribute("bran", bran);
		}


		List<UserPost> posts = new PostService().getPost(dateStr, dateEnd, cate, bran);
		request.setAttribute("posts", posts);

		List<UserComment> comments = new CommentService().getComment();
		request.setAttribute("comments", comments);

		List<Branch> branches = new BranchService().getBranch();
		request.setAttribute("branches",  branches);

		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}

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
			request.setAttribute("comment", comment);
			String dateStr = "2018-01-01";
			String dateEnd = "2020-12-31";
			String cate= null;
			String bran = null;

			List<UserPost> popos = new PostService().getPost(dateStr, dateEnd, cate, bran);
			request.setAttribute("popos", popos);

			if (StringUtils.isBlank(request.getParameter("dateStr")) != true) {
				dateStr = request.getParameter("dateStr");
				request.setAttribute("dateStr", dateStr);
			}
			if (StringUtils.isBlank(request.getParameter("dateEnd")) != true){
				dateEnd = request.getParameter("dateEnd");
				request.setAttribute("dateEnd", dateEnd);
			}
			if (StringUtils.isBlank(request.getParameter("cate")) != true){
				cate = request.getParameter("cate");
				request.setAttribute("cate", cate);
			}
			if (StringUtils.isBlank(request.getParameter("bran")) != true){
				bran = request.getParameter("bran");
				request.setAttribute("bran", bran);
			}

			List<UserPost> posts = new PostService().getPost(dateStr, dateEnd, cate, bran);
			request.setAttribute("posts", posts);

			List<UserComment> comments = new CommentService().getComment();
			request.setAttribute("comments", comments);

			List<Branch> branches = new BranchService().getBranch();
			request.setAttribute("branches",  branches);

			request.getRequestDispatcher("top.jsp").forward(request, response);

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

