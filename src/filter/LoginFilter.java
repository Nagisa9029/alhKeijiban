package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter implements Filter{
	public void doFilter(ServletRequest request, ServletResponse respons, FilterChain chain) throws IOException,ServletException{
		System.out.println("ログインチェック");

		// セッションが存在しない場合NULLを返す
		HttpSession session = ((HttpServletRequest)request).getSession(false);

		if(session != null){
			// セッションがNULLでなければ、通常どおりの遷移
			chain.doFilter(request, respons);
		}else{
			// セッションがNullならば、ログイン画面へ飛ばす
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
			dispatcher.forward(request,respons);
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
