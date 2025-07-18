package com.mi_app.api.module.authentication.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mi_app.api.module.authentication.dto.AuthenticationLoginDto;
import com.mi_app.api.module.authentication.dto.RefreshAccessTokenDto;
import com.mi_app.api.module.authentication.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/authentication")
@AllArgsConstructor
public class AuthenticationController {
  private final AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> login(@RequestBody AuthenticationLoginDto authenticationLoginDto) {
    Map<String, String> response = authenticationService.login(authenticationLoginDto);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/refresh_access_token")
  public Map<String, String> refreshAccessToken(HttpServletRequest request,
      @RequestBody RefreshAccessTokenDto refreshAccessTokenDto) {
    Map<String, String> response = authenticationService.refreshAccessToken(refreshAccessTokenDto.getRefreshToken());
    return response;
  }

}
