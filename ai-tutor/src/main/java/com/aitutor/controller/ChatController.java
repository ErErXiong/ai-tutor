package com.aitutor.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

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

    // ==================== Day 1 产出 ====================

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/chat/raw")
    public String chatRaw(@RequestParam String message) {
        Prompt prompt = new Prompt(List.of(
                new SystemMessage("你是一位耐心的学习导师，擅长用费曼学习法将复杂概念简化为通俗易懂的解释。"),
                new UserMessage(message)
        ));
        return chatModel.call(prompt)
                .getResult().getOutput().getText();
    }

    // ==================== Day 2 产出 ====================

    @GetMapping(value = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStream(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }

    @GetMapping("/chat/template")
    public String chatWithTemplate(
            @RequestParam String role,
            @RequestParam String method,
            @RequestParam String question
    ) {
        PromptTemplate template = new PromptTemplate("你是一位{role}导师，擅长用{method}教学。请回答：{question}");
        template.add("role", role);
        template.add("method", method);
        template.add("question", question);
        return chatModel.call(template.create())
                .getResult().getOutput().getText();
    }

    // ==================== 异常处理 ====================

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        return ResponseEntity.internalServerError().body(Map.of(
                "error", "AI 服务暂时不可用",
                "detail", e.getMessage() != null ? e.getMessage() : "未知错误"
        ));
    }
}