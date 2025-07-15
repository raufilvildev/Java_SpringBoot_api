package com.mi_app.api.module.mailbox.dto;

import java.time.Instant;

public interface ThreadSQLResponseDto {
  String getUuid();

  Byte getIsStarred();

  String getUsername();

  String getContent();

  Instant getCreatedAt();
}
