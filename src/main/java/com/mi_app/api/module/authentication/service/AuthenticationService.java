package com.mi_app.api.module.authentication.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mi_app.api.exception.IncorrectCredentialsException;
import com.mi_app.api.module.authentication.dto.AuthenticationLoginDto;
import com.mi_app.api.module.user.entity.User;
import com.mi_app.api.module.user.repository.UserRepository;
import com.mi_app.api.util.JwtUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;

  public Map<String, String> login(AuthenticationLoginDto authenticationLoginDto) {
    User user = userRepository.findByUsername(authenticationLoginDto.getUsername())
        .orElseThrow(() -> new IncorrectCredentialsException());

    boolean passwordMatches = passwordEncoder.matches(authenticationLoginDto.getPassword(), user.getPassword());
    if (!passwordMatches) {
      throw new IncorrectCredentialsException();
    }

    Map<String, String> response = new HashMap<>();
    response.put("refreshToken", jwtUtil.generateToken(user.getUuid(), false));
    response.put("accessToken", "Bearer " + jwtUtil.generateToken(user.getUuid(), true));
    return response;
  }

  public Map<String, String> refreshAccessToken(String refreshToken) {
    jwtUtil.validateToken(refreshToken);
    String uuid = jwtUtil.extractUuid(refreshToken);
    String accessToken = jwtUtil.generateToken(uuid, true);

    Map<String, String> response = new HashMap<>();
    response.put("accessToken", accessToken);
    return response;
  }
}
