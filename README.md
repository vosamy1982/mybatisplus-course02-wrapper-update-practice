# 课件02 LambdaUpdateWrapper + 复杂条件 + select 字段裁剪 - 学生练习工程

本工程配套课件02 第2课时，共 **5 个练习**。每个练习一道独立测试类，按顺序完成。

## 案例索引

| 序号 | 主题 | 形式 | 入口 |
|---|---|---|---|
| 1 | LambdaUpdateWrapper 基础 set/setSql/where | TODO 填空 | `src/test/.../Practice1_LambdaUpdateBasicTest.java` |
| 2 | 重构：循环 updateById → 一次 update(null, wrapper) | 重构改造 | `src/test/.../Practice2_RefactorBatchUpdateTest.java` + `src/main/.../practice2/BatchUpdateLegacyService.java` |
| 3 | 动态搜索（and/or lambda 分组） | 从零编写 | `src/test/.../Practice3_DynamicSearchTest.java` |
| 4 | select 字段裁剪 | TODO 填空 | `src/test/.../Practice4_SelectTrimTest.java` |
| 5 | 子查询 inSql / exists | 从零编写 | `src/test/.../Practice5_SubqueryTest.java` |

## 环境准备

| 项 | 值 |
|---|---|
| JDK | 17 |
| Spring Boot | 3.3.5 |
| MyBatis-Plus | 3.5.7 |
| 数据库 | MySQL 5.7 / 8.x |
| 库名 | `classDB`（账号 root / 密码 123456 —— 按本机调整 `application.yml`） |

## 启动方式速查

| 案例 | 启动方式 |
|---|---|
| 全部 | 在 IDE 里直接运行对应 `Practice{N}_XxxTest`；首次启动会自动执行 `schema.sql + data.sql` 建表灌数据 |

每个测试类的 `@Test` 方法上默认有 `@Disabled` —— 完成 TODO 后请删除注解再运行。

## 工程构建保证

主工程必须**始终可编译**（`mvn compile` 通过），即使 5 题一题没做：
- 案例 1 / 3 / 4 / 5 的 TODO 处都有合法 Java 占位（`int rows = 0;` 或 `return new ArrayList<>();`）
- 案例 2 的起点代码 `BatchUpdateLegacyService` 是完整可运行的"坏味道"实现
- 完成任何一题不会破坏其他题的代码或资源

## 答案隔离说明

- 各题的考察 API（`.and(w->)` / `.or(w->)` / `.select(...)` / `.inSql` / `.exists`）只在自己的测试文件中出现
- 案例 2 的起点 `BatchUpdateLegacyService` 用的是 `selectList + 循环 updateById`，**不含** `lambdaUpdate()` 链式写法

## 如何完成一道题

1. 打开对应的 `Practice{N}_XxxTest.java`
2. 阅读类注释中的"练习要求"
3. 在 `TODO` 标记处填代码
4. 删除 `@Disabled` 注解
5. 运行测试 —— 通过即过关
