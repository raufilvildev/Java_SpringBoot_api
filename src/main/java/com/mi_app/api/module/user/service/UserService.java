package com.mi_app.api.module.user.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mi_app.api.exception.DuplicateUserException;
import com.mi_app.api.exception.UserNotFoundException;
import com.mi_app.api.module.user.dto.UserSignupDto;
import com.mi_app.api.module.user.dto.UserUpdateDto;
import com.mi_app.api.module.user.entity.User;
import com.mi_app.api.module.user.repository.UserRepository;
import com.mi_app.api.util.JwtUtil;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;

  public User getByUuid(String uuid) {
    return userRepository.findByUuid(uuid).orElseThrow(() -> new UserNotFoundException("uuid", uuid));
  }

  public String insert(UserSignupDto userSignupDto) {
    String email = userSignupDto.getEmail();
    if (userRepository.existsByEmail(email)) {
      throw new DuplicateUserException("email", email);
    }

    String username = userSignupDto.getUsername();
    if (userRepository.existsByUsername(username)) {
      throw new DuplicateUserException("username", username);
    }

    User user = userSignupDto.generateUser();
    user.setUuid(UUID.randomUUID().toString());
    user = userRepository.save(user);

    return jwtUtil.generateToken(user.getUuid());
  }

  @Transactional
  public void update(String uuid, UserUpdateDto userUpdateDto) {
    User user = userRepository.findByUuid(uuid)
        .orElseThrow(() -> new UserNotFoundException("uuid", uuid));

    String username = userUpdateDto.getUsername();

    Optional<User> optionalUserByUsername = userRepository.findByUsername(username);

    if (optionalUserByUsername.isPresent()
        && !optionalUserByUsername.get().getUuid().equals(user.getUuid())) {
      throw new DuplicateUserException("username", username);
    }

    user.setFirstName(userUpdateDto.getFirstName());
    user.setLastName(userUpdateDto.getLastName());
    user.setUsername(userUpdateDto.getUsername());
  }

  @Transactional
  public void delete(String uuid) {
    userRepository.findByUuid(uuid)
        .orElseThrow(() -> new UserNotFoundException("uuid", uuid));
    userRepository.deleteByUuid(uuid);
  }
}
