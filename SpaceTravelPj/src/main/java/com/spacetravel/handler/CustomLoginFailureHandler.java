package com.spacetravel.handler;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String code = "bad"; // 기본: 아이디/비번 오류

        if (exception instanceof LockedException) {
            code = "locked";
        } else if (exception instanceof DisabledException) {
            code = "disabled";
        } else if (exception instanceof BadCredentialsException) {
            code = "bad";
        }

        String redirectUrl =
                "/user/login?error=true&code=" + URLEncoder.encode(code, StandardCharsets.UTF_8);

        response.sendRedirect(redirectUrl);
		
	}

}
