package com.aitutor.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Day 1 - 首个对话控制器
 * 核心抽象：ChatClient、ChatModel、Prompt、Message
 */
@RestController
public class ChatController {

    private final ChatClient chatClient;
    private final ChatModel chatModel;
    /**
     * 通过构造器注入 ChatClient.Builder，
     * Spring AI 会自动创建默认的 ChatClient 实例
     */
    public ChatController(ChatClient.Builder builder,ChatModel chatModel) {
        this.chatClient = builder
                .defaultSystem("你是一位耐心的学习导师，擅长用费曼学习法将复杂概念简化为通俗易懂的解释。")
                .build();
        this.chatModel = chatModel;
    }

    /**
     * 同步对话接口
     * GET /chat?message=什么是Spring AI
     *
     * @param message 用户消息
     * @return AI 回复文本
     */
    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    /**
     * 同步对话接口
     * chatModel形式
     * @param message
     * @return
     */
    @GetMapping("/chat/raw")
    public String chatRaw(@RequestParam String message) {
        Prompt prompt = new Prompt(List.of(new SystemMessage("你是一个耐心的学习导师，擅长用费曼学习法将复杂概念简化为通俗易懂的接受"),
                new UserMessage(message), new AssistantMessage("我会到学习方法有：1. 费曼学习法，2. 金字塔记忆法 ")));
        return chatModel.call(prompt)
                .getResult().getOutput().getText();

    }
}
