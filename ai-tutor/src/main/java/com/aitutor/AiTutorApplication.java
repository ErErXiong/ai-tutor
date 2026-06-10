package com.aitutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * AI学习导师 - 主应用入口
 * 基于 Spring AI Alibaba + 通义千问 的多Agent协作教学系统
 */
@SpringBootApplication
public class AiTutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiTutorApplication.class, args);
    }
}
