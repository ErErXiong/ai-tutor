package com.aitutor.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Day 1 - 首个对话控制器
 * 核心抽象：ChatClient、ChatModel、Prompt、Message
 */
@RestController
public class ChatController {

    private final ChatClient chatClient;
    private final ChatModel chatModel;

    public ChatController(ChatClient.Builder builder, ChatModel chatModel) {
        this.chatClient = builder
                .defaultSystem("你是一位耐心的学习导师，擅长用费曼学习法将复杂概念简化为通俗易懂的解释。")
                .build();
        this.chatModel = chatModel;
    }

    /**
     * ChatClient 同步对话
     * GET /chat?message=什么是Spring AI
     */
    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    /**
     * ChatModel 底层对话（手动组装 Prompt + Message）
     * GET /chat/raw?message=什么是RAG
     */
    @GetMapping("/chat/raw")
    public String chatRaw(@RequestParam String message) {
        Prompt prompt = new Prompt(List.of(
                new SystemMessage("你是一位耐心的学习导师，擅长用费曼学习法将复杂概念简化为通俗易懂的解释。"),
                new UserMessage(message)
        ));
        return chatModel.call(prompt)
                .getResult().getOutput().getText();
    }

    /**
     * 统一异常处理，返回友好错误信息
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        return ResponseEntity.internalServerError().body(Map.of(
                "error", "AI 服务暂时不可用",
                "detail", e.getMessage() != null ? e.getMessage() : "未知错误"
        ));
    }
}