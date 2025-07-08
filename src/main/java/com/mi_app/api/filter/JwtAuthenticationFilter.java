package com.mi_app.api.filter;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mi_app.api.exception.InvalidTokenException;
import com.mi_app.api.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String path = request.getServletPath();
    return path.startsWith("/api/users/signup")
        || path.startsWith("/api/authentication");

  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String authorizationHeader = request.getHeader("Authorization");
    try {
      if (authorizationHeader == null) {
        throw new InvalidTokenException(" No existe cabecera Authorization.");
      }

      if (!authorizationHeader.startsWith("Bearer")) {
        throw new InvalidTokenException(" El token no empieza por 'Bearer'.");
      }

      String token = authorizationHeader.replace("Bearer", "").trim();

      if (token.isEmpty()) {
        throw new InvalidTokenException(" El token es vacío.");
      }

      String uuid = jwtUtil.extractUuid(token);
      if (uuid.isBlank()) {
        throw new InvalidTokenException(" El uuid es vacío.");
      }

      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          uuid,
          null,
          Collections.emptyList() // sin roles ni permisos
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);

      request.setAttribute("uuid", uuid);

      filterChain.doFilter(request, response);
    } catch (InvalidTokenException exception) {

      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.setContentType("application/json");

      String json = "{\"message\": \"" + exception.getMessage().replace("\"", "'") + "\"}";

      response.getWriter().write(json);
      response.getWriter().flush();
    } catch (ServletException | IOException exception) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      response.setContentType("application/json");

      String json = "{\"message\": \"Ha ocurrido un error inesperado. Vuelve a intentarlo más tarde."
          + exception.getMessage() + "\"}";

      response.getWriter().write(json);
      response.getWriter().flush();
    }

  }
}
