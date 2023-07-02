# Interqu-Release

# Running Locally

To automatically set sensitive environment variables from .env, it is highly recommended for developers to install `direnv`. Please reach out to team for .env file.

# Docker-compose Deployment

Below is a sample docker-compose.yml configuration for creating the container.

```
---
version: "3.8"

services:
  interqu-spring-boot:
    container_name: interqu-fe
    image: lefanhu/interqu-spring-boot:latest
    stdin_open: true
    tty: true
    restart: unless-stopped
    ports:
      - '8080:8080'
    env_file:
      - './.env'
```
