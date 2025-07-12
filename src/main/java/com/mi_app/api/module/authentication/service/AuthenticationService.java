package com.mi_app.api.module.authentication.service;

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

  public String login(AuthenticationLoginDto authenticationLoginDto) {
    User user = userRepository.findByUsername(authenticationLoginDto.getUsername())
        .orElseThrow(() -> new IncorrectCredentialsException());

    boolean passwordMatches = passwordEncoder.matches(authenticationLoginDto.getPassword(), user.getPassword());
    if (!passwordMatches) {
      throw new IncorrectCredentialsException();
    }

    return jwtUtil.generateToken(user.getUuid());
  }
}
