@echo off
REM 在此设置你的百炼 API Key
set AI_DASHSCOPE_API_KEY=your-api-key-here
echo ========================================
echo   AI Tutor - 启动中...
echo   访问: http://localhost:8080/chat?message=你好
echo ========================================
"C:\Program Files\gradle-9.5.1\bin\gradle.bat" bootRun --no-daemon -q