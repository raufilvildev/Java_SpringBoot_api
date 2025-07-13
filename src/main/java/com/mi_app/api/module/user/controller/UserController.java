package com.mi_app.api.module.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mi_app.api.module.user.dto.UserResponseDto;
import com.mi_app.api.module.user.dto.UserSignupDto;
import com.mi_app.api.module.user.dto.UserUpdateDto;
import com.mi_app.api.module.user.entity.User;
import com.mi_app.api.module.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping("")
  public ResponseEntity<UserResponseDto> getByUuid(HttpServletRequest request) {
    String uuid = request.getAttribute("uuid").toString();
    User user = userService.getByUuid(uuid);
    return ResponseEntity.ok(UserResponseDto.generateUserResponseDto(user));
  }

  @PostMapping("/signup")
  public ResponseEntity<Map<String, String>> insert(@RequestBody @Valid UserSignupDto userSignupDto) {
    Map<String, String> response = userService.insert(userSignupDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("")
  public ResponseEntity<Map<String, String>> update(HttpServletRequest request,
      @RequestBody @Valid UserUpdateDto userUpdateDto) {
    String uuid = request.getAttribute("uuid").toString();
    userService.update(uuid, userUpdateDto);

    Map<String, String> response = new HashMap<>();
    response.put("message", "El usuario con uuid = " + uuid + " ha sido actualizado correctamente.");
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("")
  public ResponseEntity<Map<String, String>> delete(HttpServletRequest request) {
    String uuid = request.getAttribute("uuid").toString();
    userService.delete(uuid);

    Map<String, String> response = new HashMap<>();
    response.put("message", "El usuario con uuid = " + uuid + " ha sido borrado correctamente.");
    return ResponseEntity.ok(response);
  }
}
