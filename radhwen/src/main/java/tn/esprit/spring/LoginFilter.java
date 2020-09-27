package tn.esprit.spring;
import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tn.esprit.spring.controller.LoginController;
public class LoginFilter implements Filter {

	  private static final Logger LOGGER =
	      LoggerFactory.getLogger(LoginFilter.class);

	  public static final String LOGIN_PAGE = "/loginsignup";

	  @Override
	  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {    
		    HttpServletRequest request = (HttpServletRequest) req;
		    HttpServletResponse response = (HttpServletResponse) res;
		    HttpSession session = request.getSession(false);
		    String loginURI = request.getContextPath() + "/loginsignup";

		    boolean loggedIn = session != null && session.getAttribute("user") != null;
		    boolean loginRequest = request.getRequestURI().equals(loginURI);
		    boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

		    if (loggedIn || loginRequest || resourceRequest) {
		        chain.doFilter(request, response);
		    } else {
		        response.sendRedirect(loginURI);
		    }
	  }
	
	 

	  @Override
	  public void destroy() {
	    // close resources
	  }
	}
