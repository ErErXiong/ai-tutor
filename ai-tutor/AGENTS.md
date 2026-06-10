# AI Tutor 项目 AGENTS.md

## 项目信息
- 项目：AI学习导师（Spring AI Alibaba 多Agent协作教学系统）
- 技术栈：Spring Boot 3.4.x + Spring AI Alibaba 1.1.2.x + 通义千问 + Gradle
- JDK 17+，Gradle：C:\Program Files\gradle-9.5.1\bin\gradle.bat
- API Key：环境变量 AI_DASHSCOPE_API_KEY 或 run.bat 中设置

## 进度管理（强制）
- 任务开始前：先读取 docs/PROGRESS.md 获取当前进度
- 任务结束时：更新 docs/PROGRESS.md 标记完成状态和产出
- 速记卡：写入 docs/daily/YYYY-MM-DD.md

## 学习模式（严格遵循）
1. STEP 0 复习抽查 (10min)：速问速答+面试模拟，间隔1/3/7天
2. STEP 1 新知识讲解 (20min)：类比→流程图→源码，标注项目落地位置
3. STEP 2 手写补全 (40min)：先编译验证通过，再注释核心逻辑用 TODO 标记
   - 格式：// TODO [W1D2-任务1]: 说明 // 提示：xxx
   - 补充：你写 Spec（目标+约束+验收）→ 发给 Codex 看它怎么实现 → 对比你手写的
4. STEP 3 审查校准 + Codex 双审查 (15min)
   - AI(我)审查：逻辑、风格、安全、设计模式
   - Codex 审查：同一段代码丢给 Codex 审查 → 你对比两份报告
   - 学"AI能发现什么，发现不了什么"
   - 不懂就停，深讲到底
5. STEP 4 面试五连问 (10min)：①是什么 ②解决什么 ③原理 ④对比 ⑤踩坑
6. STEP 5 Git提交 (10min)
   - 让 Codex 帮你写 commit message，你审查修改
   - 格式：[W1D2] feat: xxx，git add -p分块提交
7. STEP 6 当天速记卡写入 docs/daily/

## Codex 使用训练（贯穿全程）
- 你写 Spec → Codex 实现 → 你审查（你当架构师，Codex 当执行者）
- 编译报错 → 先丢给 Codex 分析 → 再自己修（学用 AI 调试）
- 同一段代码 → 我审查 + Codex 审查 → 你对比两份报告
- Git commit → Codex 写 message → 你审查修改

## 核心原则
- 三线合一：学知识+面面试+出项目，所有代码在工程中生长
- 项目代码：搭骨架→验证通过→注释核心→用户手写补全
- 与项目无关的代码不写入工程
- 用户说不会立即停住深讲，不带问号往前走