package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;

@WebFilter(urlPatterns={"/users", "/newuser", "/edituser"})
public class BranchPositionFilter implements Filter{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException{

		HttpSession session = ((HttpServletRequest)request).getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser != null){
			if (loginUser.getBranchId() == 1 && loginUser.getPositionId() == 1){
				chain.doFilter(request, response);
			}else{
				List<String> messages = new ArrayList<String>();
				messages.add("権限のないIDでアクセスされました");
				session.setAttribute("errorMessages", messages);
				((HttpServletResponse) response).sendRedirect("./");
				//request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}else{
			List<String> messages = new ArrayList<String>();
			messages.add("IDとパスワードでログインしてください");
			session.setAttribute("errorMessages", messages);
			((HttpServletResponse) response).sendRedirect("login");
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
