package com.hbnu.course02.practice;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hbnu.course02.practice.entity.Employee;
import com.hbnu.course02.practice.mapper.EmployeeMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Practice4：select 字段裁剪（TODO 填空题）
 * 对应课件 3.4 节
 *
 * 【练习要求】
 * 列表查询场景：只查 4 列 id / name / department / salary，
 * 其余字段在返回结果中应为 null（节省传输带宽）。
 *
 * 在 wrapper 上加上字段裁剪调用，并加一个 WHERE 限定查"技术部"。
 *
 * 完成后删除 @Disabled 注解，运行测试应通过断言。
 */
@SpringBootTest
class Practice4_SelectTrimTest {

    @Autowired
    private EmployeeMapper empMapper;

    @Test
    @Disabled("完成 TODO 后删除此注解")
    void practice4_列表只查4列_技术部() {
        LambdaQueryWrapper<Employee> wrapper = Wrappers.<Employee>lambdaQuery()
                .eq(Employee::getDepartment, "技术部");

        // ============ TODO 在 wrapper 上加字段裁剪，只查 id / name / department / salary ============
        //
        // 提示：用方法引用形式（Employee::getId 这种）
        //
        // wrapper.???;
        //
        // ============ TODO 结束 ============

        List<Employee> list = empMapper.selectList(wrapper);

        // 验证：技术部 3 人 + 未查字段为 null
        assertThat(list).hasSize(3);
        list.forEach(e -> {
            assertThat(e.getId()).as("id 应被查到").isNotNull();
            assertThat(e.getName()).as("name 应被查到").isNotNull();
            assertThat(e.getDepartment()).as("department 应被查到").isNotNull();
            assertThat(e.getSalary()).as("salary 应被查到").isNotNull();
            assertThat(e.getDescription()).as("description 未被查，应为 null").isNull();
            assertThat(e.getHireDate()).as("hireDate 未被查，应为 null").isNull();
            assertThat(e.getStatus()).as("status 未被查，应为 null").isNull();
        });
    }
}
