# Blog Backend Application

## 项目简介

这是一个博客后端应用程序，使用 Spring Boot 构建，集成了 MyBatis 进行数据库操作，提供了用户管理、留言管理、评论管理、公告管理等功能，同时使用 JWT 进行身份验证，支持跨域请求。

## 功能特性

1. **用户管理**：用户注册、登录、密码找回、用户信息更新等。
2. **留言管理**：用户可以提交留言，管理员可以回复和删除留言。
3. **评论管理**：用户可以对文章进行评论，支持多级评论。
4. **公告管理**：管理员可以发布、更新、删除公告。
5. **统计信息**：统计用户数量和文章数量。
6. **搜索功能**：支持文章和公告的关键词搜索。

## 技术栈

- **后端框架**：Spring Boot
- **数据库操作**：MyBatis
- **身份验证**：JWT
- **跨域处理**：CORS
- **日期处理**：Jackson

## 项目结构

```plaintext
src
├── main
│   ├── java
│   │   └── org
│   │       └── example
│   │           ├── config
│   │           │   ├── SecurityConfig.java
│   │           │   └── JacksonObjectMapper.java
│   │           ├── controller
│   │           │   ├── UserController.java
│   │           │   └── ArticleController.java
│   │           ├── entity
│   │           │   ├── Message.java
│   │           │   ├── User.java
│   │           │   ├── Comment.java
│   │           │   ├── Announcements.java
│   │           │   ├── Statistics.java
│   │           │   ├── Category.java
│   │           │   └── Result.java
│   │           ├── mapper
│   │           │   ├── UserMapper.java
│   │           │   ├── CommentMapper.java
│   │           │   ├── AnnouncementsMapper.java
│   │           │   ├── StatisticsMapper.java
│   │           │   ├── CategoryMapper.java
│   │           │   └── MessageMapper.java
│   │           ├── service
│   │           │   ├── CategoryService.java
│   │           │   ├── UserService.java
│   │           │   ├── AnnouncementsService.java
│   │           │   ├── SearchService.java
│   │           │   └── MessageService.java
│   │           ├── service.impl
│   │           │   ├── AnnouncementsServiceImpl.java
│   │           │   └── MessageServiceImpl.java
│   │           ├── utils
│   │           │   ├── JwtUtils.java
│   │           │   └── BCryptUtils.java
│   │           ├── exception
│   │           │   └── UserContextHolder.java
│   │           └── BlogBackendApplication.java
│   └── resources
│       ├── mapper
│       │   ├── CommentMapper.xml
│       │   ├── AnnouncementsMapper.xml
│       │   └── StatisticsMapper.xml
│       └── application.properties
└── test
    └── java
        └── org
            └── example
                └── BlogBackendApplicationTests.java
```

## 环境要求
- Java 8 或更高版本
- Maven
- MySQL 数据库

## 配置说明

### 数据库配置

在 `src/main/resources/application.properties` 中配置数据库连接信息：

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blog_backend?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
### JWT 配置

在 src/main/java/org/example/utils/JwtUtils.java 中配置 JWT 的签名密钥和过期时间：
```java
private static final String signKey = "7LrS5a6d5piv5qWt7L2c5a6Y5a6d5piv5qWt7L2c5a6Y5a6d5piv5qWt7L2c5a6Y5a6d5piv5qWt7L2c5a6Y=";
private static final Long expire = 604800000L;
```
### CORS 配置

在 src/main/java/org/example/config/SecurityConfig.java 中配置允许的跨域请求信息：

```java
configuration.setAllowedOrigins(Arrays.asList("https://946641.xyz", "http://localhost:5173"));
```

## 运行步骤

1. 克隆项目到本地：

```bash
git clone https://github.com/your-repo/blog-backend.git
cd blog-backend
```

1. 构建项目：
```bash
mvn clean package
```
1. 运行项目：
```bash
java -jar target/blog-backend-1.0-SNAPSHOT.jar
```
## API 文档

### 用户相关接口

- `POST /api/users/check`：检查用户是否存在

### 文章相关接口

- `PUT /api/articles/{id}`：更新文章信息

## 测试

在 src/test/java/org/example/BlogBackendApplicationTests.java 中编写了一些测试用例，可以使用 Maven 命令运行测试：
```bash
mvn test
```
## 贡献

如果你想为这个项目做出贡献，请遵循以下步骤：

1. Fork 这个仓库
2. 创建一个新的分支：`git checkout -b feature/your-feature`
3. 提交你的更改：`git commit -m 'Add some feature'`
4. 推送到你的分支：`git push origin feature/your-feature`
5. 提交一个 Pull Request

## 许可证
本项目采用 MIT 许可证。