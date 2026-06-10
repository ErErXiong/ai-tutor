# Java → Java-AI 转型学习计划：AI学习导师

## 概要

5周脱产冲刺，孵化 **「AI学习导师」**——基于费曼学习法 + 刻意练习 + 间隔重复的7Agent协作教学系统。

- **技术栈**：Spring Boot 3.4.x + Spring AI Alibaba + 阿里云百炼（通义千问）+ JDK 17+
- **构建工具**：Gradle（Groovy DSL）
- **AI编程工具**：Codex 贯穿全程——先手写理解原理，再用AI加速
- **项目地址**：GitHub 公开仓库

## 项目架构速览

```
用户输入"我想学 Spring AI"
         │
         ▼
┌───────────┐  ┌───────────┐  ┌───────────┐  ┌───────────┐
│ 学习规划师  │→│ 知识检索师  │→│  答疑导师   │→│  实践导师   │
│ 拆解目标   │  │ RAG检索   │  │ 费曼讲解   │  │ 生成练习   │
└───────────┘  └───────────┘  └───────────┘  └─────┬─────┘
                                                    │ 用户提交代码
                                                    ▼
                                             ┌───────────┐
                                             │ 代码审查师  │
                                             │ 审查+反馈   │
                                             └─────┬─────┘
                                                   │ 通过
┌───────────┐                                ┌─────┴─────┐
│ 总结归纳师  │ ◀────────────────────────────── │  出题导师   │
│ 报告+错题本 │                                │ 间隔重复   │
└───────────┘                                └───────────┘
```

**涵盖的学习理论**：费曼学习法、刻意练习、间隔重复、主动回忆、项目驱动、输出倒逼输入、即时反馈

**六种实践类型**：编程题 / 设计题 / 费曼讲解 / 代码补全 / Debug练习 / 渐进项目

---

## 第1周：Spring AI 基座 + RAG → 打造「知识检索师」

### Day 1：项目骨架 + 首个对话

**任务**
1. 创建 Spring Boot 3.4.x + JDK 17+ + Gradle 项目
2. 配置 `spring-ai-alibaba-starter-dashscope` 依赖
3. 在[百炼控制台](https://bailian.console.aliyun.com/?tab=model#/api-key)获取 API Key
4. 手写 `ChatClient` 实现与通义千问的首次对话（同步模式）
5. 理解 `ChatModel`、`ChatClient`、`Prompt`、`Message` 核心抽象

**项目落地**：ChatBot雏形——这是"答疑导师"的起点

**AI工具**：手写ChatClient后让Codex生成配置文件和POM依赖；解释`ChatClient.Builder`源码

**资源**：[快速入门](https://java2ai.com/docs/quick-start/) | [百炼API Key](https://bailian.console.aliyun.com/) | [参考文档](https://docs.spring.io/spring-ai/reference/) | [示例项目](https://github.com/spring-ai-alibaba/examples)

**速记卡**：ChatModel(大模型门面) | ChatClient(对话入口Builder) | Prompt(System+User+History) | Message(role+content)

---

### Day 2：流式输出 + Prompt Engineering

**任务**：流式对话(SSE)、Prompt Template、System Message角色设定

**项目落地**：给"答疑导师"设定角色——"你是一位耐心的学习导师，用费曼学习法简化复杂概念"

**AI工具**：让Codex生成多种Prompt模板；生成前端EventSource代码

**资源**：[ChatClient文档](https://docs.spring.io/spring-ai/reference/api/chatclient.html) | [Prompt Engineering](https://spring.io/blog/2025/04/14/spring-ai-prompt-engineering-patterns) | [Spring AI入门](https://spring.io/blog/2025/05/20/your-first-spring-ai-1) | [Anthropic Prompt工程](https://docs.anthropic.com/en/docs/build-with-claude/prompt-engineering)

**速记卡**：Flux\<String\>(响应式流) | SSE(单向推送) | PromptTemplate(防注入) | System Message(角色设定)

---

### Day 3：Structured Output + Function Calling 初探

**任务**：JSON→Java Bean映射、@Tool注解、简单Agent循环

**项目落地**：答疑导师拥有"搜索资料"工具

**AI工具**：让Codex审查Tool的description；根据API文档生成HTTP Tool

**资源**：[Structured Output](https://docs.spring.io/spring-ai/reference/api/chatclient.html#_structured_output) | [Advisors详解](https://spring.io/blog/2024/10/02/supercharging-your-ai-applications-with-spring-ai-advisors) | [Awesome Spring AI](https://github.com/spring-ai-community/awesome-spring-ai)

**速记卡**：Structured Output(JSON→Bean) | @Tool(LLM可调用的方法) | entity()(自动反序列化) | Function Calling(LLM决策→框架执行)

---

### Day 4：Embedding 原理 + 向量数据库

**任务**：文档向量化全链路、ES向量存储、相似度检索

**项目落地**：知识库存储——用户上传的学习资料向量化

**AI工具**：让Codex生成不同格式的DocumentReader；对比ES vs pgvector

**资源**：[Embedding文档](https://docs.spring.io/spring-ai/reference/api/embeddings.html) | [Vector Store](https://docs.spring.io/spring-ai/reference/api/vectordbs.html) | [ES KNN](https://www.elastic.co/guide/en/elasticsearch/reference/current/knn-search.html) | [HiChunk分块策略](https://github.com/TencentCloudADP/hichunk)

**速记卡**：Embedding(文本→向量) | TokenTextSplitter(分块+overlap) | 余弦相似度 | ES KNN

---

### Day 5：RAG 检索增强生成全流程

**任务**：提问→Embedding→检索→上下文拼接→生成，RetrievalAugmentationAdvisor

**项目落地**：知识检索师可运行

**AI工具**：让Codex批量生成RAG测试用例；分析检索噪声

**资源**：[RAG文档](https://docs.spring.io/spring-ai/reference/api/retrieval-augmented-generation.html) | [RAG概念](https://java2ai.com/ecosystem/spring-ai/reference/concepts/) | [benym全栈RAG](https://github.com/benym/spring-ai-fullstack-demo)

**速记卡**：RAG(检索增强生成) | QueryTransformer(问题重写) | Advisor(责任链增强) | RetrievalAugmentationAdvisor

---

### Day 6：RAG 高级优化

**任务**：Rerank精排、混合检索(BM25+向量→RRF融合)、分块策略对比

**项目落地**：检索质量优化

**AI工具**：让Codex生成分块策略对比测试

**资源**：[RAGFlow](https://github.com/infiniflow/ragflow) | [RRF算法](https://www.elastic.co/guide/en/elasticsearch/reference/current/rrf.html)

**速记卡**：BM25(关键词检索) | RRF(融合排序) | Rerank(精排) | 混合检索

---

### Day 7：测试 + 周总结

**第1周面试题**

1. RAG核心流程？各阶段优化手段？ → [RAG文档](https://docs.spring.io/spring-ai/reference/api/retrieval-augmented-generation.html)
2. 向量相似度检索算法及适用场景？ → [ES KNN](https://www.elastic.co/guide/en/elasticsearch/reference/current/knn-search.html)
3. 如何评估RAG检索质量？ → [RAGFlow评估](https://github.com/infiniflow/ragflow)
4. 文本分块策略对RAG的影响？ → [HiChunk](https://github.com/TencentCloudADP/hichunk)
5. Prompt Template为什么优于字符串拼接？ → [Prompt Engineering](https://spring.io/blog/2025/04/14/spring-ai-prompt-engineering-patterns)
6. SSE和WebSocket的区别？ → [SSE规范](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events)
7. Spring Boot 4.0有哪些变化？(加分) → [SB4迁移指南](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-4.0-Migration-Guide)

---

## 第2周：Agent + MCP → 打造「答疑导师」+「实践导师」雏形

### Day 1：Agent理念 + ReAct模式

**任务**：手写ReAct循环——思考→选工具→执行→观察→再思考

**项目落地**：答疑导师核心引擎

**资源**：[Anthropic构建有效Agent](https://www.anthropic.com/engineering/building-effective-agents) | [微软AI Agent入门](https://github.com/microsoft/ai-agents-for-beginners) | [Agent核心公式](https://github.com/chenlanqing/Java-Programmer) | [Agentic Patterns](https://spring.io/blog/2025/01/21/spring-ai-agentic-patterns)

**速记卡**：Agent=LLM+Planning+Memory+Tools | ReAct=Reasoning+Acting交替 | 最大轮次

---

### Day 2：@Tool注解 + 多工具注册

**任务**：定义多个Tool、工具发现与注册

**项目落地**：答疑导师拥有搜索+翻译+查代码等工具组合

**资源**：[Tools文档](https://docs.spring.io/spring-ai/reference/api/tools.html) | [@Tool示例](https://github.com/spring-ai-alibaba/examples) | [Function Calling详解](https://java2ai.com/ecosystem/spring-ai/reference/concepts/)

**速记卡**：@Tool(LLM可调用) | ToolCallback(回调接口) | 工具注册(让LLM"看见"能力)

---

### Day 3：Agent循环完善

**任务**：并行工具调用、结果反馈、异常重试

**项目落地**：答疑导师并行搜索多资料源

**资源**：[Agent Framework教程](https://java2ai.com/docs/frameworks/agent-framework/tutorials/agents)

**速记卡**：并行工具调用 | Tool Message反馈 | 重试策略(限次+退避)

---

### Day 4：MCP协议原理

**任务**：理解MCP(JSON-RPC 2.0 over stdio/SSE)、工具发现、资源暴露

**项目落地**：知识检索师作为MCP Server

**资源**：[MCP规范](https://modelcontextprotocol.io/) | [MCP Spec GitHub](https://github.com/modelcontextprotocol/specification) | [Spring AI MCP](https://docs.spring.io/spring-ai/reference/api/clients/mcp-client.html) | [MCP Annotations](https://github.com/spring-ai-community/mcp-annotations) | [MCP OAuth2](https://spring.io/blog/2025/05/19/spring-ai-mcp-client-oauth2)

**速记卡**：MCP=Model Context Protocol | JSON-RPC 2.0 | stdio/SSE传输 | Tool Discovery

---

### Day 5：MCP Client实现

**任务**：用Spring AI实现MCP Client连接外部Server

**项目落地**：学习导师通过MCP Client连接知识Server

**资源**：[MCP Client示例](https://github.com/spring-projects/spring-ai-examples/tree/main/model-context-protocol) | [动态工具更新](https://spring.io/blog/2025/05/04/spring-ai-dynamic-tool-updates-with-mcp) | [MCP Annotations Client](https://github.com/spring-ai-community/mcp-annotations)

**速记卡**：McpSyncClient(同步客户端) | listTools()(发现工具) | MCP Tool vs @Tool

---

### Day 6：MCP Server实现

**任务**：用Spring AI实现MCP Server暴露学习资料查询工具

**项目落地**：知识检索师作为独立MCP Server

**资源**：[MCP Server指南](https://docs.spring.io/spring-ai/reference/api/clients/mcp-server.html) | [MCP Server OAuth2](https://spring.io/blog/2025/04/02/mcp-server-oauth2) | [MCP DB示例](https://github.com/anjeludo/spring-ai-mcp) | [Druid MCP](https://github.com/iunera/druid-mcp-server)

**速记卡**：@McpTool(暴露工具) | @McpResource(暴露资源) | MCP Server架构

---

### Day 7：Agent + MCP集成 + 周测试

**第2周面试题**

1. Agent核心公式？和传统Workflow区别？ → [Anthropic Agent](https://www.anthropic.com/engineering/building-effective-agents)
2. Function Calling完整流程？LLM如何选工具？ → [Tools文档](https://docs.spring.io/spring-ai/reference/api/tools.html)
3. MCP协议原理？和REST API区别？ → [MCP规范](https://modelcontextprotocol.io/)
4. 如何防止Agent无限循环？ → [Context Engineering](https://java2ai.com/docs/frameworks/agent-framework/tutorials/hooks)
5. ReAct中Reasoning和Acting如何交替？ → [ReAct论文](https://arxiv.org/abs/2210.03629)
6. MCP传输层stdio vs SSE适用场景？ → [MCP Spec](https://spec.modelcontextprotocol.io/)

---

## 第3周：多Agent编排 + Graph → 串通完整学习流程

### Day 1：SequentialAgent + ParallelAgent

**任务**：手写顺序/并行编排

**资源**：[Agent Framework教程](https://java2ai.com/docs/frameworks/agent-framework/tutorials/agents) | [Multi-Agent Patterns](https://github.com/alibaba/spring-ai-alibaba/tree/main/examples/multiagent-patterns)

**速记卡**：SequentialAgent(顺序执行) | ParallelAgent(并行执行+聚合)

---

### Day 2：RoutingAgent + LoopAgent

**任务**：路由分发、循环迭代

**资源**：[Routing示例](https://github.com/spring-ai-alibaba/examples) | [Agentic Blog](https://spring.io/blog/2025/01/21/spring-ai-agentic-patterns)

**速记卡**：RoutingAgent(按条件分发) | LoopAgent(循环直到条件满足)

---

### Day 3：Graph API工作流

**任务**：节点定义、边、条件路由、状态管理

**资源**：[Graph快速入门](https://java2ai.com/docs/frameworks/graph-core/quick-start) | [LangGraph概念](https://langchain-ai.github.io/langgraph/) | [DeepResearch](https://github.com/spring-ai-alibaba/deepresearch)

**速记卡**：Graph State(全局上下文) | 条件路由(选下一节点) | 流式输出(每节点实时推送)

---

### Day 4：学习导师完整Graph编排

**任务**：编排7个Agent的完整工作流

---

### Day 5：A2A通信（Nacos）

**任务**：Agent通过Nacos注册发现

**资源**：[A2A文档](https://java2ai.com/docs/frameworks/agent-framework/advanced/a2a) | [Nacos](https://nacos.io/)

**速记卡**：A2A=Agent-to-Agent | Nacos注册发现 | 分布式Agent协作

---

### Day 6：实践导师 + 代码审查师完善

**任务**：六种任务生成 + 代码审查逻辑

---

### Day 7：多Agent集成测试

**第3周面试题**

1. 多Agent编排常见模式？ → [Agentic Patterns](https://spring.io/blog/2025/01/21/spring-ai-agentic-patterns)
2. A2A通信怎么实现？ → [A2A文档](https://java2ai.com/docs/frameworks/agent-framework/advanced/a2a)
3. Graph工作流和传统代码编排区别？ → [Graph Quick Start](https://java2ai.com/docs/frameworks/graph-core/quick-start)
4. 如何处理Agent级联失败？ → [Context Engineering](https://java2ai.com/docs/frameworks/agent-framework/tutorials/hooks)
5. ParallelAgent结果如何聚合？ → [Multi-Agent Patterns](https://github.com/alibaba/spring-ai-alibaba/tree/main/examples/multiagent-patterns)
6. 分布式Agent上下文如何传递？ → [OverAllState](https://java2ai.com/docs/frameworks/graph-core/quick-start)

---

## 第4周：上下文工程 + 记忆系统 + 前端 → MVP完成

### Day 1：上下文工程六大策略

**任务**：HITL、上下文压缩/编辑、调用限制、工具重试、动态工具选择

**资源**：[Context Engineering](https://java2ai.com/docs/frameworks/agent-framework/tutorials/hooks) | [Advisors详解](https://spring.io/blog/2024/10/02/supercharging-your-ai-applications-with-spring-ai-advisors)

**速记卡**：上下文压缩(摘要历史) | HITL(关键决策确认) | 动态工具(按需调整)

---

### Day 2：Chat Memory

**任务**：Redis短期记忆 + 向量库长期记忆 + 学习进度持久化

**资源**：[Chat Memory](https://docs.spring.io/spring-ai/reference/api/chatclient.html#chat-memory) | [Redis](https://redis.io/docs/) | [Spring Session](https://docs.spring.io/spring-session/reference/)

**速记卡**：短期记忆(Redis对话历史) | 长期记忆(向量库知识图谱) | 记忆检索(语义搜索)

---

### Day 3：前端Chat UI

**任务**：Tailwind CSS对话界面 + Agent选择面板 + 学习进度侧栏

**资源**：[benym全栈项目](https://github.com/benym/spring-ai-fullstack-demo) | [Tailwind CSS](https://tailwindcss.com/) | [UI风格参考](https://www.aura.build)

---

### Day 4：SSE流式 + 思考过程可视化

**任务**：thinking/content分离、打字机效果、Agent状态显示

**资源**：[SSE规范](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events) | [WebFlux SSE](https://docs.spring.io/spring-framework/reference/web/webflux/reactive-spring.html)

---

### Day 5：前后端联调

---

### Day 6：端到端集成测试

**资源**：[Spring Boot Testing](https://docs.spring.io/spring-boot/reference/testing/) | [Admin调试工具](https://github.com/spring-ai-alibaba/spring-ai-alibaba-admin)

---

### Day 7：MVP完成

**第4周面试题**

1. 上下文工程和Prompt Engineering的关系？ → [Context Engineering](https://java2ai.com/docs/frameworks/agent-framework/tutorials/hooks)
2. 短期记忆和长期记忆分别怎么实现？ → [Chat Memory](https://docs.spring.io/spring-ai/reference/api/chatclient.html#chat-memory)
3. 上下文过长如何压缩？ → [Advisors](https://spring.io/blog/2024/10/02/supercharging-your-ai-applications-with-spring-ai-advisors)
4. Human-in-the-Loop如何实现？ → [HITL](https://java2ai.com/docs/frameworks/agent-framework/tutorials/hooks)
5. SSE断线重连怎么处理？ → [EventSource API](https://developer.mozilla.org/en-US/docs/Web/API/EventSource)
6. 多轮对话Token超限怎么办？ → [TokenTextSplitter](https://docs.spring.io/spring-ai/reference/api/etl-pipeline.html)

---

## 第5周：源码阅读 + 部署 + 面试冲刺

### Day 1：ReActAgent源码深度阅读

**资源**：[Spring AI Alibaba源码](https://github.com/alibaba/spring-ai-alibaba)

---

### Day 2：Lynxe(JManus)架构分析

**资源**：[Lynxe源码](https://github.com/spring-ai-alibaba/Lynxe) | [JManus技术分析](https://developer.aliyun.com/article/1658152)

---

### Day 3：Docker部署

**资源**：[Docker Docs](https://docs.docker.com/) | [docker-compose](https://docs.docker.com/compose/)

---

### Day 4：README + 架构图 + API文档

**资源**：[PlantUML](https://plantuml.com/) | [SpringDoc](https://springdoc.org/)

---

### Day 5：简历包装 + 面试知识点总复习

**简历亮点模板**

```
项目：AI学习导师 —— 基于多Agent协作的智能教学系统
技术栈：Spring Boot 3.x + Spring AI Alibaba + 通义千问 + Elasticsearch + Redis + Docker
核心实现：
- 设计了7个专业Agent（规划师、检索师、答疑师、实践导师、审查师、出题师、总结师）
- 基于费曼学习法+刻意练习+间隔重复的混合教学策略
- 实现RAG知识库检索（混合检索+RRF融合+精排Rerank）
- 基于MCP协议实现Agent间工具共享
- 使用Graph工作流编排完整学习流程
- A2A协议支持Agent分布式协作（Nacos注册发现）
- 实践环节支持6种练习类型，AI自动审查代码并反馈
```

**资源**：[阿里云AI白皮书](https://developer.aliyun.com/ebook/8479)

---

### Day 6：模拟面试

**资源**：[AI Agent十问十答](https://mp.weixin.qq.com/s/keZ4H12NElW-Ew0R1Puayg)

---

### Day 7：投简历 + 结业总结

**第5周面试题（综合拔高）**

1. Spring AI Alibaba Agent Framework核心架构？ → [源码](https://github.com/alibaba/spring-ai-alibaba)
2. 从零设计多Agent协作平台？ → [Lynxe架构](https://github.com/spring-ai-alibaba/Lynxe)
3. Agent中如何保证确定性和可控性？ → [Func-Agent模式](https://github.com/Lynxe-public/Lynxe-public-prompts)
4. 大模型幻觉在Agent场景下如何缓解？ → [RAG文档](https://docs.spring.io/spring-ai/reference/api/retrieval-augmented-generation.html)
5. MCP和A2A的定位？什么场景用哪个？ → [MCP](https://modelcontextprotocol.io/) vs [A2A](https://java2ai.com/docs/frameworks/agent-framework/advanced/a2a)
6. 你项目中最大的技术难点和解决方案？ → 你的GitHub项目

---

## Codex技能成长路径

```
第1周 ───── 第2周 ───── 第3周 ───── 第4周 ───── 第5周
基础操作     多文件重构   Spec驱动    全栈开发    高级技巧
代码生成     协议开发    项目管理    前后端联调   源码分析
测试用例     MCP工具    架构图      文档生成     模拟面试
配置生成     集成测试    日志分析    UI生成       部署配置
```

**核心原则**：先手写再加速 | 你定Spec它写代码 | Git细粒度提交 | 审查每行代码

---

## 可选前沿技术路线

| # | 技术 | 插入时机 | 耗时 |
|---|------|----------|------|
| 1 | Voice Agent（实时语音） | 第2周MCP后 | +1天 |
| 2 | Docker代码沙箱执行器 | 第4周实践导师后 | +1天 |
| 3 | Multimodal（图片理解） | 第3周多Agent后 | +1天 |
| 4 | GraphRAG | 第1周RAG后 | +1天 |
| 5 | LangChain4j | 第2周对比学习 | +半天 |
| 6 | RAGFlow | 第1周RAG后 | +半天 |
| 7 | OpenViking | 第4周记忆系统 | +半天 |
| 8 | AgentScope Java | 第3周后 | +半天 |
| 9 | Agentic-ADK | 第3周了解 | +半天 |
| 10 | Coze Studio | 第4周了解 | +半天 |

---

## 资源总汇

### 必Star仓库

| 仓库 | 说明 |
|------|------|
| [alibaba/spring-ai-alibaba](https://github.com/alibaba/spring-ai-alibaba) | Java企业级Agent全家桶 |
| [spring-projects/spring-ai](https://github.com/spring-projects/spring-ai) | Spring AI官方核心 |
| [spring-ai-alibaba/Lynxe](https://github.com/spring-ai-alibaba/Lynxe) | 阿里多Agent平台（前身JManus） |
| [spring-ai-alibaba/examples](https://github.com/spring-ai-alibaba/examples) | 官方示例大全 |
| [spring-ai-community/awesome-spring-ai](https://github.com/spring-ai-community/awesome-spring-ai) | 精选资源合集 |
| [benym/spring-ai-fullstack-demo](https://github.com/benym/spring-ai-fullstack-demo) | 全栈ChatAgent项目 |
| [littleStar0o0/llm-roadmap](https://github.com/littleStar0o0/llm-roadmap) | LLM企业级学习路线图v2.5 |
| [chenlanqing/Java-Programmer](https://github.com/chenlanqing/Java-Programmer) | Java AI知识库 |

### 核心文档

| 文档 | 链接 |
|------|------|
| Spring AI Alibaba官网 | https://java2ai.com |
| Spring AI参考文档 | https://docs.spring.io/spring-ai/reference/ |
| MCP官方规范 | https://modelcontextprotocol.io |
| Spring官方博客(AI系列) | https://spring.io/blog |
| 百炼API Key | https://bailian.console.aliyun.com/ |
| 阿里云AI架构白皮书 | https://developer.aliyun.com/ebook/8479 |
| Spring Boot 4.0迁移指南 | https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-4.0-Migration-Guide |

### 面试准备核心

| 资料 | 链接 |
|------|------|
| Anthropic构建有效Agent | https://www.anthropic.com/engineering/building-effective-agents |
| 微软AI Agent入门 | https://github.com/microsoft/ai-agents-for-beginners |
| LLM路线图(含招聘生态) | https://github.com/littleStar0o0/llm-roadmap |

---

## 假设与前提

- Java 17+、Spring Boot 3.x、Gradle、Redis、Docker基础已具备
- 阿里云百炼API Key（新用户免费100万token）
- 每天8-10小时，Git每日提交，GitHub公开仓库
- **最终产出**：AI学习导师——7Agent协作的智能教学系统
- **Spring Boot 3.4.x学习用**，面试展示对4.0迁移的了解作加分项
- **Docker代码沙箱**记录为进阶功能，MVP用"用户本地执行+AI审查"
- **构建工具**：Gradle（Groovy DSL）+ Gradle Wrapper
