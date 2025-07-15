package com.mi_app.api.module.mailbox.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mi_app.api.module.mailbox.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
  Optional<Message> findByUuid(String uuid);

}
