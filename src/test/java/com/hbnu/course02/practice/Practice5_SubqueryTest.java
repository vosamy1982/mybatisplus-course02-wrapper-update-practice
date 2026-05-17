package com.hbnu.course02.practice;

import com.hbnu.course02.practice.entity.Employee;
import com.hbnu.course02.practice.mapper.EmployeeMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Practice5：子查询 inSql / exists（从零编写题）
 * 对应课件 3.4 节
 *
 * <h3>练习要求</h3>
 * 实现下面 <b>两个</b> 方法，分别用 {@code inSql} 和 {@code exists} 写子查询。
 *
 * <p>安全铁律：子查询字符串只写常量，不要拼接任何变量（SQL 注入风险）。
 *
 * <p>完成后删除 {@code @Disabled} 注解。
 */
@SpringBootTest
class Practice5_SubqueryTest {

    @Autowired
    private EmployeeMapper empMapper;

    /**
     * 任务①：用 {@code inSql} 查询"所属部门 company_id = 1 的员工"。
     *
     * <p>提示：用 {@code .inSql(Employee::getDepartmentId, "SELECT id FROM dept WHERE company_id = 1")}
     */
    List<Employee> findEmployeesOfCompany1_inSql() {
        // ============ TODO 从零编写 ============
        return new ArrayList<>();
        // ============ TODO 结束 ============
    }

    /**
     * 任务②：用 {@code exists} 查询"所属部门 active = 1 的员工"。
     *
     * <p>提示：用 {@code .exists("SELECT 1 FROM dept WHERE dept.id = employee.department_id AND dept.active = 1")}
     */
    List<Employee> findActiveDeptEmployees_exists() {
        // ============ TODO 从零编写 ============
        return new ArrayList<>();
        // ============ TODO 结束 ============
    }

    @Test
    @Disabled("完成 TODO 后删除此注解")
    void practice5_1_inSql_company1() {
        List<Employee> r = findEmployeesOfCompany1_inSql();
        // company_id=1 的部门：技术部(id=1)、产品部(id=2)
        // 员工：张/李/王（技术部）+ 赵/钱（产品部） → 5 人
        assertThat(r).hasSize(5);
    }

    @Test
    @Disabled("完成 TODO 后删除此注解")
    void practice5_2_exists_active部门() {
        List<Employee> r = findActiveDeptEmployees_exists();
        // active=1 的部门：1/2/3；active=0 的部门：4
        // 部门=4 的员工只有 周设计 → 排除
        // 其余 7 人都命中
        assertThat(r).hasSize(7);
    }
}
