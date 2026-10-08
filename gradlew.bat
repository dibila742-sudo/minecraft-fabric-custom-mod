@echo off
setlocal

where gradlew.bat >nul 2>&1
if not errorlevel 1 (
  call gradlew.bat %*
) else (
  echo Gradle wrapper is not present. Please run: gradle build
  exit /b 1
)
