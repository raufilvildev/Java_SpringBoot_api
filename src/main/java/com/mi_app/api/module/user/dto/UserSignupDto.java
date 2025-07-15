package com.mi_app.api.module.user.dto;

import com.mi_app.api.module.user.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserSignupDto {
  @NotBlank(message = "El campo nombre es obligatorio.")
  @Size(min = 3, max = 50, message = "El campo nombre debe tener entre 3 y 50 caracteres.")
  private String firstName;

  @NotBlank(message = "El campo apellidos es obligatorio.")
  @Size(min = 3, max = 50, message = "El campo apellidos debe tener entre 3 y 50 caracteres.")
  private String lastName;

  @NotBlank(message = "El campo correo electrónico es obligatorio.")
  @Size(max = 255, message = "El correo electrónico no debe exceder los 255 caracteres.")
  @Email(message = "Formato de correo electrónico inválido.")
  private String email;

  @NotBlank(message = "El campo nombre de usuario es obligatorio.")
  @Size(min = 3, max = 50, message = "El campo nombre de usuario debe tener entre 3 y 50 caracteres.")
  private String username;

  @NotBlank(message = "El campo contraseña es obligatorio.")
  @Size(min = 8, max = 100, message = "El campo contraseña debe tener entre 8 y 100 caracteres.")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=\\[\\]{};':\"\\\\|,.<>\\/?\\-]).{8,}$", message = "La contraseña debe tener mínimo 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial.")
  private String password;

  public User generateUser() {

    User user = new User();
    user.setFirstName(this.getFirstName());
    user.setLastName(this.getLastName());
    user.setEmail(this.getEmail());
    user.setUsername(this.getUsername());
    user.setPassword(this.getPassword());

    return user;
  }
}
