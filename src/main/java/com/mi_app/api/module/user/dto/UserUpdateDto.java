package com.mi_app.api.module.user.dto;

import com.mi_app.api.module.user.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserUpdateDto {
  @NotBlank(message = "El campo nombre es obligatorio.")
  @Size(min = 3, max = 50, message = "El campo nombre debe tener entre 3 y 50 caracteres.")
  private String firstName;

  @NotBlank(message = "El campo apellidos es obligatorio.")
  @Size(min = 3, max = 50, message = "El campo apellidos debe tener entre 3 y 50 caracteres.")
  private String lastName;

  @NotBlank(message = "El campo nombre de usuario es obligatorio.")
  @Size(min = 3, max = 50, message = "El campo nombre de usuario debe tener entre 3 y 50 caracteres.")
  private String username;

  public User generateUser() {

    User user = new User();
    user.setFirstName(this.getFirstName());
    user.setLastName(this.getLastName());
    user.setUsername(this.getUsername());

    return user;
  }
}
