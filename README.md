 # Ignite Server

基于 Spring Boot 和 Apache Ignite 的分布式缓存服务器。

A distributed caching server based on Spring Boot and Apache Ignite.

## 技术栈 / Tech Stack

- Java 17
- Spring Boot 2.7.0
- Apache Ignite 2.17.0
- Jetty 9.4.49.v20220914
- H2 Database 1.4.197

## 主要功能 / Main Features

- 分布式缓存 / Distributed Caching
- SQL 查询支持 / SQL Query Support
- REST API 接口 / REST API Interface
- Docker 支持 / Docker Support
- Jenkins CI/CD 流水线 / Jenkins CI/CD Pipeline

## 快速开始 / Quick Start

### 构建项目 / Build Project

```bash
mvn clean install
```

### 运行服务 / Run Service

```bash
mvn spring-boot:run
```

服务将在 8082 端口启动。

The service will start on port 8082.

### 使用 Docker / Using Docker

```bash
mvn clean package dockerfile:build
docker run -p 8082:8082 ignite-server
```

### 停止服务 / Stop Service

#### Linux/MacOS

```bash
# 查找占用 8082 端口的进程
lsof -i :8082

# 终止进程
kill -9 <PID>
```

#### Windows

```bash
# 查找占用 8082 端口的进程
netstat -ano | findstr :8082

# 终止进程
taskkill /PID <PID> /F
```

## REST API 接口 / REST API Endpoints

### Ignite REST API

- `GET /ignite?cmd=version` - 获取 Ignite 版本
- `GET /ignite?cmd=top` - 获取拓扑信息
- `GET /ignite?cmd=node` - 获取节点信息

### 自定义 REST API

- `GET /api/hello` - Hello 接口
- `GET /api/cache/{cacheName}` - 获取缓存信息
- `POST /api/cache/{cacheName}` - 创建缓存
- `DELETE /api/cache/{cacheName}` - 删除缓存

## SQL 功能 / SQL Functionality

应用程序支持通过 Ignite 的 SQL 引擎执行 SQL 查询。您可以使用任何支持 JDBC 的工具连接到数据库。

The application supports SQL queries through Ignite's SQL engine. You can use any JDBC-compliant tool to connect to the database.

## 配置说明 / Configuration

主要配置文件：

Main configuration files:

- `application.properties` - Spring Boot 配置
- `ignite-config.xml` - Ignite 配置

## 依赖说明 / Dependencies

主要依赖包括：

Main dependencies include:

- Spring Boot Web
- Spring Boot Jetty
- Apache Ignite Core
- Apache Ignite Spring
- Apache Ignite Indexing
- Apache Ignite REST HTTP
- H2 Database
- Lombok

## 开发环境 / Development Environment

- JDK 17
- Maven 3.6+
- IDE: IntelliJ IDEA / Eclipse
- Docker (可选 / optional)
- Jenkins (可选 / optional)

## 注意事项 / Notes

1. 确保 8082 端口未被占用
2. 首次运行时会自动创建 Ignite 工作目录
3. 使用 Java 17 运行项目

1. Ensure port 8082 is not in use
2. Ignite work directory will be created automatically on first run
3. Use Java 17 to run the project

## Jenkins 集成 / Jenkins Integration

项目包含 Jenkinsfile 用于 CI/CD 流水线。流水线包括：
- 构建
- 测试
- 构建 Docker 镜像
- 推送 Docker 镜像

The project includes a Jenkinsfile for CI/CD pipeline. The pipeline includes:
- Build
- Test
- Build Docker image
- Push Docker image