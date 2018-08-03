package filter;

import java.io.IOException;

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

@WebFilter(urlPatterns={"/index.jsp", "/logout", "/NewComment", "/newpost", "/DeleteComment", "/delete"})
public class LoginFilter implements Filter{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException{

		HttpSession session = ((HttpServletRequest)request).getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser != null){
			chain.doFilter(request, response);
		}else{
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
