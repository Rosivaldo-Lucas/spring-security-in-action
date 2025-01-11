package com.github.rosivaldolucas.spring_security_in_action.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RequestValidationFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    String header = request.getHeader("Request-ID");

    if (header == null || header.isBlank()) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    filterChain.doFilter(request, response);
  }

}
