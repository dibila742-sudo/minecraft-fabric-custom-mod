#!/usr/bin/env sh

auto() {
  if command -v ./gradlew >/dev/null 2>&1; then
    ./gradlew "$@"
  else
    echo "Gradle wrapper is not present. Please run: gradle build"
    exit 1
  fi
}

auto "$@"
