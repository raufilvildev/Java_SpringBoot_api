package com.mi_app.api.module.mailbox.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mi_app.api.module.mailbox.dto.ThreadResponseDto;
import com.mi_app.api.module.mailbox.service.ThreadService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/threads")
@AllArgsConstructor
public class ThreadController {
  private final ThreadService threadService;

  @GetMapping("")
  public List<ThreadResponseDto> getAll(HttpServletRequest request) {
    return threadService.findByUserUuid(request.getAttribute("uuid").toString());
  }

}
