package br.com.rest.springboot.security.jwt;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenFilter extends GenericFilterBean{

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	public JwtTokenFilter(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	//Filter that be executed on each requisition
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse res = (HttpServletResponse) response;
		
		try {
			String token = tokenProvider.resolveToken((HttpServletRequest) request);
			if (token != null && tokenProvider.validateToken(token)) {
				Authentication auth = tokenProvider.getAuthentication(token);
				if (auth != null) {
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
			chain.doFilter(request, response);		
		} catch (Exception e) {
			res.setStatus(HttpStatus.UNAUTHORIZED.value());
			res.getWriter().write("Invalid token");
			res.getWriter().flush();
            return;
		}
	}
}