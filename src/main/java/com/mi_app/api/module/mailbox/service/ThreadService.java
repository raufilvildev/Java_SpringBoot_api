package com.mi_app.api.module.mailbox.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mi_app.api.module.mailbox.dto.ThreadResponseDto;
import com.mi_app.api.module.mailbox.dto.ThreadSQLResponseDto;
import com.mi_app.api.module.mailbox.repository.ThreadRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ThreadService {
  private final ThreadRepository threadRepository;

  public List<ThreadResponseDto> findReceivedByUserUuid(String uuid) {
    List<ThreadSQLResponseDto> listThreadSQLResponseDto = threadRepository.findReceivedByUserUuid(uuid);
    return ThreadResponseDto.generateListThreadResponseDto(listThreadSQLResponseDto);
  }

  public List<ThreadResponseDto> findSentByUserUuid(String uuid) {
    List<ThreadSQLResponseDto> listThreadSQLResponseDto = threadRepository.findSentByUserUuid(uuid);
    return ThreadResponseDto.generateListThreadResponseDto(listThreadSQLResponseDto);
  }
}
