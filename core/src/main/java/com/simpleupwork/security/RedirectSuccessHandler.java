package com.simpleupwork.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectSuccessHandler implements AuthenticationSuccessHandler {

	private final String successUrl;
	private final RedirectStrategy strategy = new DefaultRedirectStrategy();

	public RedirectSuccessHandler(String successUrl) {
		this.successUrl = successUrl;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		strategy.sendRedirect(httpServletRequest, httpServletResponse, successUrl);
	}
}
