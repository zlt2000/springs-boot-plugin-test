#### 测试 Spring Boot 动态加载 Jar 包
1. 启动时动态加载
2. 运行时动态加载

&nbsp;
#### 测试步骤
1. 修改 `application.properties` 中的 `targetUrl` 参数值为本机 plugin 目录中 jar 的实际绝对路径
2. 测试启动时动态加载jar： `http://127.0.0.1:8080/test`
3. 测试运行时动态加载jar： `http://127.0.0.1:8080/reload`