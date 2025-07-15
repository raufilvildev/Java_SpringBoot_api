package com.mi_app.api.module.mailbox.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ThreadResponseDto {
  String uuid;
  Boolean isStarred;
  String username;
  String content;
  Instant createdAt;

  public static ThreadResponseDto generateThreadResponseDto(ThreadSQLResponseDto threadSQLResponseDto) {
    return new ThreadResponseDto(threadSQLResponseDto.getUuid(), threadSQLResponseDto.getIsStarred() == 1,
        threadSQLResponseDto.getUsername(),
        threadSQLResponseDto.getContent(), threadSQLResponseDto.getCreatedAt());
  }

  public static List<ThreadResponseDto> generateListThreadResponseDto(
      List<ThreadSQLResponseDto> listThreadSQLResponseDto) {
    List<ThreadResponseDto> listThreadResponseDto = new ArrayList<>();

    listThreadSQLResponseDto.forEach(thread -> {
      listThreadResponseDto.add(ThreadResponseDto.generateThreadResponseDto(thread));
    });
    return listThreadResponseDto;
  }
}
