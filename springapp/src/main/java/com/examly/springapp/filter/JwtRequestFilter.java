package com.examly.springapp.filter;

import com.examly.springapp.MyUserDetails;
import com.examly.springapp.services.MyUserDetailsService;
import com.examly.springapp.utils.JwtUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired private MyUserDetailsService myUserDetailsService;

  @Autowired private JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain)
      throws ServletException, IOException {
    final String authorizationHeader = request.getHeader("Authorization");

    String username = null;
    String jwt = null;

    if (authorizationHeader != null &&
        authorizationHeader.startsWith("Bearer ")) {
      jwt = authorizationHeader.substring(7);
      username = jwtUtil.extractEmail(jwt);
    }

    if (username != null &&
        SecurityContextHolder.getContext().getAuthentication() == null) {
      MyUserDetails myUserDetails =
          this.myUserDetailsService.loadUserByUsername(username);

      if (jwtUtil.validateToken(jwt, myUserDetails)) {

        UsernamePasswordAuthenticationToken
            usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                    myUserDetails, null, myUserDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(
            usernamePasswordAuthenticationToken);
      }
    }

    filterChain.doFilter(request, response);
  }
}
