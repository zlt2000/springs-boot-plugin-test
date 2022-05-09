#### 详细说明
[《Spring Boot 如何热加载jar实现动态插件？》](https://mp.weixin.qq.com/s/Fg-jsoFon5LwsPAaBbeiew)

#### 目录结构
- **plugin-impl**：插件实现
- **plugin-users**：插件使用样例
- **plugins**：插件包存放目录

#### 测试 Spring Boot 动态加载 Jar 包
1. 启动时动态加载
2. 运行时动态加载

#### 测试步骤
进入 plugin-users 工程
1. 修改 `application.properties` 中的 `targetUrl` 参数值为本机 plugin 目录中 jar 的实际绝对路径
2. 启动 plugin-users 工程
3. 测试启动时动态加载jar：`http://127.0.0.1:8080/test`
4. 测试运行时动态加载jar：`http://127.0.0.1:8080/reload`