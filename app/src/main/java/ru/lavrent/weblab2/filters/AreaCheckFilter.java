package ru.lavrent.weblab2.filters;

import java.io.IOException;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.lavrent.weblab2.HTTPUtils;

@WebFilter(urlPatterns = "/check-area", dispatcherTypes = { DispatcherType.FORWARD, DispatcherType.REQUEST })
public class AreaCheckFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    Boolean isValid = (Boolean) request.getAttribute("valid");
    if (isValid == null || !isValid) {
      HTTPUtils.sendErrorHtml((HttpServletRequest) request, (HttpServletResponse) response,
          "request was not validated - use ./controller instead");
      return;
    }
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
}
