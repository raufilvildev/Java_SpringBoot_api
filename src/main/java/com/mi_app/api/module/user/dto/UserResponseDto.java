package com.mi_app.api.module.user.dto;

import com.mi_app.api.module.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
  private final String uuid;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final String username;

  public static UserResponseDto generateUserResponseDto(User user) {
    return new UserResponseDto(user.getUuid(), user.getFirstName(), user.getLastName(), user.getEmail(),
        user.getUsername());
  }
}
