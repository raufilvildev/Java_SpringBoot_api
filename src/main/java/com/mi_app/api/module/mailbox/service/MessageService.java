package com.mi_app.api.module.mailbox.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mi_app.api.module.mailbox.entity.Message;
import com.mi_app.api.module.mailbox.repository.MessageRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessageService {
  private final MessageRepository messageRepository;

  public Optional<Message> getByUuid(String uuid) {
    return messageRepository.findByUuid(uuid);
  }
}
