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

import org.apache.commons.lang.StringUtils;

import beans.Branch;
import beans.IndexUser;
import beans.Position;
import beans.User;
import exception.NoRowsUpdatedRuntimeException;
import service.BranchService;
import service.PositionService;
import service.UserService;

@WebServlet(urlPatterns = { "/edit" })
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		Integer ID = Integer.parseInt(request.getParameter("id"));
		List<IndexUser> editUser = new UserService().getShowUser(ID);

		List<Branch> branches = new BranchService().getBranch();
		request.setAttribute("branches",  branches);

		List<Position> positions = new PositionService().getPosition();
		request.setAttribute("positions", positions);

		request.setAttribute("editUser", editUser.get(0));
		request.getRequestDispatcher("EditUser.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		User editUser = getEditUser(request);

		if (isValid(request, messages) == true) {

			try {
				new UserService().update(editUser);
			} catch (NoRowsUpdatedRuntimeException e) {
				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				session.setAttribute("errorMessages", messages);
				request.setAttribute("editUser", editUser);
				request.getRequestDispatcher("EditUser.jsp").forward(request, response);
				return;
			}

			//session.setAttribute("loginUser", editUser);

			response.sendRedirect("./users");
		} else {
			session.setAttribute("errorMessages", messages);
			request.setAttribute("editUser", editUser);
			List<Branch> branches = new BranchService().getBranch();
			request.setAttribute("branches",  branches);

			List<Position> positions = new PositionService().getPosition();
			request.setAttribute("positions", positions);

			request.getRequestDispatcher("EditUser.jsp").forward(request, response);
		}
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {

		User editUser = new User();
		editUser.setId(Integer.parseInt(request.getParameter("id")));
		editUser.setName(request.getParameter("name"));
		editUser.setAccount(request.getParameter("account"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setIsStopped(Integer.parseInt(request.getParameter("is_stopped")));
		editUser.setBranchId(Integer.parseInt(request.getParameter("branch_id")));
		editUser.setPositionId(Integer.parseInt(request.getParameter("position_id")));
		return editUser;
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
		//boolean CheckAccount = new UserService().getUser(account);

		//if(CheckAccount == false){
		//	messages.add("このアカウント名は既に使用されています");
		//}
		if ( account.length() < 6 || 20 < account.length() || !Pattern.compile(patternA).matcher(account).find()) {
			messages.add("アカウントは半角英数字、6文字以上20文字以下で入力してください");
		}
		if (10 < name.length()) {
			messages.add("名前は10文字以下で入力してください");
		}
		if (password.length() != 0) {
			if (password.length() < 6 ||  20 < password.length() || !Pattern.compile(patternB).matcher(account).find()) {
				messages.add("パスワードは記号を含む半角文字で6文字以上20文字以下で入力してください");
			}
		}
		if (password.length() != 0){
			if (!Pattern.compile(password).matcher(passwordTest).find()) {
				messages.add("パスワードと確認用パスワードが一致していません");
			}
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
		if (StringUtils.isEmpty(account) == true) {
			messages.add("アカウント名を入力してください");
		}
		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");
		}
		//if (StringUtils.isEmpty(password) == true) {
		//	messages.add("パスワードを入力してください");
		//}
		/*if (StringUtils.isEmpty(branch_id) == true) {
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