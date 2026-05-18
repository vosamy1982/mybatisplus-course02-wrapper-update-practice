package com.hbnu.course02.practice;

import com.hbnu.course02.practice.entity.Employee;
import com.hbnu.course02.practice.mapper.EmployeeMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Practice1：LambdaUpdateWrapper 基础（TODO 填空题）
 * 对应课件 3.2 节
 *
 * 【练习要求】
 * 把所有 status = 0 且 update_time < cutoff 的员工的 status 改为 2（离职）。
 *   1. 构造 Wrappers.<Employee>lambdaUpdate()
 *   2. 用 .set(...) 设置新的 status 值
 *   3. 用 .eq(...) 限定原 status
 *   4. 用 .lt(...) 限定 update_time
 *   5. 调用 empMapper.update(null, wrapper)
 *
 * 完成后删除 @Disabled 注解，运行测试应通过断言。
 */
@SpringBootTest
@Transactional
class Practice1_LambdaUpdateBasicTest {

    @Autowired
    private EmployeeMapper empMapper;

    @Test
    @Disabled("完成 TODO 后删除此注解")
    void practice1_批量改status() {
        LocalDateTime cutoff = LocalDateTime.parse("2024-12-31T00:00:00");

        // ============ TODO 在下面构造 wrapper 并调用 update ============
        //
        // 目标 SQL：
        //   UPDATE employee SET status = 2
        //   WHERE status = 0 AND update_time < ?
        //
        // 提示：用 Wrappers.<Employee>lambdaUpdate() 链式调用
        //
        int rows = 0; // TODO 替换为 empMapper.update(null, 你构造的 wrapper);
        //
        // ============ TODO 结束 ============

        // 验证：data.sql 里 status=0 的员工是 王技术(update_time=2024-09-01) 和 吴老员(update_time=2024-01-01)
        // 都 < 2024-12-31，所以应该改 2 条
        assertThat(rows).isEqualTo(2);
        System.out.println("受影响行数 = " + rows + "（期望 2）");
    }
}
