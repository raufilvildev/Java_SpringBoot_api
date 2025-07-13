package com.mi_app.api.module.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RefreshAccessTokenDto {
  private final String refreshToken;
}
