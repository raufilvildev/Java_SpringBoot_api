package com.mi_app.api.module.mailbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mi_app.api.module.mailbox.dto.ThreadSQLResponseDto;
import com.mi_app.api.module.mailbox.entity.Thread;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {
  @Query(value = """
          SELECT t1.uuid AS uuid, t3.is_important AS isImportant, t6.username, t2.content, t2.created_at AS createdAt
          FROM threads t1
          JOIN messages t2 ON t1.id = t2.thread_id
          JOIN participants t3 ON t2.id = t3.message_id
          JOIN users t4 ON t3.user_id = t4.id AND t4.uuid = :uuid
          JOIN (
            SELECT thread_id, MAX(created_at) AS last_created_at
            FROM messages
            GROUP BY thread_id
          ) latest ON latest.thread_id = t2.thread_id AND latest.last_created_at = t2.created_at
          JOIN participants t5 ON t2.id = t5.message_id AND t5.role = 'FROM'
          JOIN users t6 ON t5.user_id = t6.id
      """, nativeQuery = true)
  List<ThreadSQLResponseDto> findByUserUuid(String uuid);
}
