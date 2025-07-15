package com.mi_app.api.module.mailbox.entity;

import java.time.Instant;

import com.mi_app.api.module.mailbox.enums.ParticipantRole;
import com.mi_app.api.module.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "participants")
@Data
public class Participant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ParticipantRole role;

  @Column(name = "is_read", nullable = false)
  private Boolean isRead;

  @Column(name = "is_deleted", nullable = false)
  private Boolean isDeleted;

  @Column(name = "is_archived", nullable = false)
  private Boolean isArchived;

  @Column(name = "is_draft", nullable = false)
  private Boolean isDraft;

  @Column(name = "is_important", nullable = false)
  private Boolean isImportant;

  @Column(name = "read_at", nullable = false, insertable = false, updatable = false)
  private Instant readAt;

  @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
  private Instant updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "message_id", nullable = false)
  private Message message;
}