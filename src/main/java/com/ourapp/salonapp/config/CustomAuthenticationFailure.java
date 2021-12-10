package com.ourapp.salonapp.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailure extends SimpleUrlAuthenticationFailureHandler {
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, 
      HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {

        setDefaultFailureUrl("/login?error=true");
        
         super.onAuthenticationFailure(request, response, exception);
         
         String errorMessage = "Bad Credentials";
         
         if(exception.getClass().isAssignableFrom(DisabledException.class)) {
             errorMessage = "Account is disabled please contact your Admin";
         }
         
         HttpSession session = request.getSession();
         session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
    }

}