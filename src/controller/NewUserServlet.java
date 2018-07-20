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

import beans.User;
import service.UserService;

@WebServlet(urlPatterns = { "/newuser" })
public class NewUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("NewUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
    		HttpServletResponse response) throws IOException, ServletException {

    	List<String> messages = new ArrayList<String>();

    	HttpSession session = request.getSession();
    	if (isValid(request, messages) == true) {

    		User user = new User();
    		user.setIsStopped(Integer.parseInt(request.getParameter("is_stopped")));
    		user.setAccount(request.getParameter("account"));
    		user.setName(request.getParameter("name"));
    		user.setPassword(request.getParameter("password"));
    		user.setBranchId(Integer.parseInt(request.getParameter("branch_id")));
    		user.setPositionId(Integer.parseInt(request.getParameter("position_id")));

    		new UserService().register(user);
System.out.println(request.getParameter("name"));
    		response.sendRedirect("./");
    	} else {
    		session.setAttribute("errorMessages", messages);
    		response.sendRedirect("newuser");
    	}

    }

    private boolean isValid(HttpServletRequest request, List<String> messages) {
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        if (StringUtils.isEmpty(account) == true) {
            messages.add("アカウント名を入力してください");
        }
        if (StringUtils.isEmpty(password) == true) {
            messages.add("パスワードを入力してください");
        }

        if (messages.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

}