package com.mi_app.api.module.authentication.service;

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

  public String login(AuthenticationLoginDto authenticationLoginDto) {
    User user = userRepository.findByUsername(authenticationLoginDto.getUsername())
        .orElseThrow(() -> new IncorrectCredentialsException());

    if (!authenticationLoginDto.getPassword().equals(user.getPassword())) {
      throw new IncorrectCredentialsException();
    }

    return jwtUtil.generateToken(user.getUuid());
  }
}
