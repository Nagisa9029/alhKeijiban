package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Branch;
import beans.Position;
import beans.User;
import service.BranchService;
import service.PositionService;
import service.UserService;

@WebServlet(urlPatterns = { "/newuser" })
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Branch> branches = new BranchService().getBranch();
		request.setAttribute("branches",  branches);

		List<Position> positions = new PositionService().getPosition();
		request.setAttribute("positions", positions);

		request.getRequestDispatcher("NewUser.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		User user = getUser(request);

		if (isValid(request, messages) == true) {

			new UserService().register(user);

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			request.setAttribute("user", user);
			response.sendRedirect("newuser");
		}
	}

	private User getUser(HttpServletRequest request)
			throws IOException, ServletException {

		User user = new User();
		user.setAccount(request.getParameter("account"));
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setIsStopped(Integer.parseInt(request.getParameter("is_stopped")));
		user.setBranchId(Integer.parseInt(request.getParameter("branch_id")));
		user.setPositionId(Integer.parseInt(request.getParameter("position_id")));
		return user;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String account = request.getParameter("account");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String passwordTest = request.getParameter("passwordTest");
		Integer branch_id = Integer.parseInt(request.getParameter("branch_id"));
		Integer position_id = Integer.parseInt(request.getParameter("position_id"));
		String patternA = "^[\\w]+$" ;
		String patternB = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]";


		if (account == null) {
			messages.add("このアカウント名は既に使用されています");
		}
		if ( account.length() < 6 || 20 < account.length() || !Pattern.compile(patternA).matcher(account).find()) {
			messages.add("アカウントは半角英数字、6文字以上20文字以下で入力してください");
		}
		if (10 < name.length()) {
			messages.add("名前は10文字以下で入力してください");
		}
		if (password.length() < 6 ||  20 < password.length() || !Pattern.compile(patternB).matcher(account).find()) {
			messages.add("パスワードは記号を含む半角文字で6文字以上20文字以下で入力してください");
		}
		//if (password != passwordTest) {
		//	messages.add("パスワードと確認用パスワードが一致していません");
		//}
		if (branch_id == 1 && position_id == 3) {
			messages.add("支店と部署・役職の組み合わせを確認してください");
		}
		if (branch_id == 1 && position_id == 4) {
			messages.add("支店と部署・役職の組み合わせを確認してください");
		}
		if (branch_id == 2 && position_id == 1) {
			messages.add("支店と部署・役職の組み合わせを確認してください");
		}
		if (branch_id == 3 && position_id == 1) {
			messages.add("支店と部署・役職の組み合わせを確認してください");
		}
		if (branch_id == 4 && position_id == 1) {
			messages.add("支店と部署・役職の組み合わせを確認してください");
		}
		if (branch_id == 2 && position_id == 2) {
			messages.add("支店と部署・役職の組み合わせを確認してください");
		}
		if (branch_id == 3 && position_id == 2) {
			messages.add("支店と部署・役職の組み合わせを確認してください");
		}
		if (branch_id == 4 && position_id == 2) {
			messages.add("支店と部署・役職の組み合わせを確認してください");
		}

		/*if (StringUtils.isEmpty(account) == true) {
			messages.add("アカウント名を入力してください");
		}
		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		if (StringUtils.isEmpty(branch_id) == true) {
			messages.add("支店が選択されていません");
		}
		if (StringUtils.isEmpty(position_id) == true) {
			messages.add("部署・役職が選択されていません");
		}*/


		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}