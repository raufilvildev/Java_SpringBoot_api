package com.mi_app.api.module.mailbox.service;

import java.util.ArrayList;
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

  public List<ThreadResponseDto> findByUserUuid(String uuid) {
    List<ThreadSQLResponseDto> listThreadSQLResponseDto = threadRepository.findByUserUuid(uuid);
    List<ThreadResponseDto> threads = new ArrayList<>();
    listThreadSQLResponseDto.forEach(thread -> {
      threads.add(new ThreadResponseDto(thread.getUuid(), thread.getIsImportant() == 1, thread.getUsername(),
          thread.getContent(), thread.getCreatedAt()));
    });
    return threads;
  }
}
