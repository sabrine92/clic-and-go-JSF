package filters;

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

import entities.Traveler;
import beans.LoginBean;

@WebFilter("/pages/traveler/*")
public class trFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				"loginBean");
		if ((loginBean != null) && (loginBean.isLoggedIn())
				&& (loginBean.getUser().getClass() == Traveler.class)) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + "/login.jsf");
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
