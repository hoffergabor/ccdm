package hu.iboard.coandco.datamagic.retail.veszmont.presentation.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class AuthenticationFilter implements Filter {

	private static Logger logger = Logger.getLogger(AuthenticationFilter.class);

	private static final String PUBLIC_URL_PATTERN_COUNT = "PUBLIC_URL_PATTERN_COUNT";
	private static final String PUBLIC_URL_PATTERN = "PUBLIC_URL_PATTERN_";

	private FilterConfig filterConfig;
	private List<String> urlPatterns = new ArrayList<String>(0);

	public void destroy() {

		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		//chain.doFilter(request, response);
		
		boolean isAuthenticated = (session != null && session.getAttribute("loggedInPartner") != null);
		boolean isAuthenticatedAdmin = (session != null && session.getAttribute("loggedInAdmin") != null);
		
		if (isAuthenticated || isAuthenticatedAdmin || isPublicURL(req.getRequestURI(), urlPatterns)) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect(req.getContextPath() + "/index");
			return;
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;
		String patternCountStr = PropertyUtil.getAppProperty(PUBLIC_URL_PATTERN_COUNT);
		try {
			int patternCount = Integer.parseInt(patternCountStr);
			for (int index = 0; index < patternCount; index++) {
				String leadingZeros = (index < 10 ? "00" : (index < 100 ? "0" : ""));
				String pattern = PropertyUtil.getAppProperty(PUBLIC_URL_PATTERN + leadingZeros + index);
				urlPatterns.add(pattern);
			}
			logger.info("AuthenticationFilter has been initialized to allow " + urlPatterns.size() + " URL pattern(s) for public.");
		} catch (Exception e) {
			logger.error("Could not parse application property: [" + PUBLIC_URL_PATTERN_COUNT + "]! AuthenticationFilter will protect all pages.");
		}
	}

	private boolean isPublicURL(String url, List<String> patterns) {
		boolean retVal = false;
		for (String pattern : patterns) {
			if (url.matches(pattern)) {
				retVal = true;
				break;
			}
		}
		return retVal;
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public List<String> getUrlPatterns() {
		return urlPatterns;
	}

	public void setUrlPatterns(List<String> urlPatterns) {
		this.urlPatterns = urlPatterns;
	}

}
