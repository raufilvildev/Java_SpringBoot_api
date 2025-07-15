package com.mi_app.api.module.mailbox.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ThreadResponseDto {
  String uuid;
  Boolean isImportant;
  String username;
  String content;
  Instant createdAt;
}
