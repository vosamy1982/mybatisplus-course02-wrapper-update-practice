package com.hbnu.course02.practice;

import com.hbnu.course02.practice.entity.Employee;
import com.hbnu.course02.practice.mapper.EmployeeMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Practice3：动态搜索（从零编写题）
 * 对应课件 3.3 节 "and/or 嵌套"
 *
 * <h3>练习要求</h3>
 * 从零实现 {@link #search} 方法，要求生成的查询满足以下规则：
 * <ol>
 *   <li>keyword 非空时：name 或 position 任意一个模糊匹配 keyword（OR 关系，括号包住）</li>
 *   <li>department 非空时：精确等于 department</li>
 *   <li>minSalary 非空时：salary &gt;= minSalary</li>
 *   <li>maxSalary 非空时：salary &lt;= maxSalary</li>
 *   <li>2 ~ 4 之间是 AND 关系</li>
 *   <li>结果按 hire_date 倒序排序</li>
 * </ol>
 *
 * <p>关键提示：keyword 那组 OR 必须用 lambda 分组（{@code .and(w -> w.like(...).or().like(...))}），
 * 不能用顶层裸 {@code .or()}（会引发课件 3.3 节讲到的"意图扭曲"坑）。
 *
 * <p>完成后删除 {@code @Disabled} 注解。
 */
@SpringBootTest
class Practice3_DynamicSearchTest {

    @Autowired
    private EmployeeMapper empMapper;

    /**
     * TODO 从零编写 —— 这个方法目前返回空列表。
     */
    List<Employee> search(String keyword, String department, BigDecimal minSalary, BigDecimal maxSalary) {
        // ============ TODO 在下面构造查询 wrapper 并返回 selectList 结果 ============
        //
        // 提示：用 Wrappers.<Employee>lambdaQuery()
        //       条件方法支持 (condition, field, value) 三参数重载，传 condition 控制是否拼接
        //
        return new ArrayList<>();
        //
        // ============ TODO 结束 ============
    }

    @Test
    @Disabled("完成 TODO 后删除此注解")
    void practice3_1_仅按keyword搜索() {
        List<Employee> r = search("工程师", null, null, null);
        // position 含"工程师"的有 3 人：张/李/王（高级工程师/工程师/初级工程师）
        // name 都不含"工程师"
        assertThat(r).hasSize(3);
    }

    @Test
    @Disabled("完成 TODO 后删除此注解")
    void practice3_2_keyword加department() {
        List<Employee> r = search("产品", "产品部", null, null);
        // department=产品部 AND (name 含"产品" OR position 含"产品")
        // 产品部：赵产品 + 钱产品
        //   赵产品 name 含"产品"、position=产品经理 含"产品" → 命中
        //   钱产品 name 含"产品"、position=产品助理 含"产品" → 命中
        assertThat(r).hasSize(2);
    }

    @Test
    @Disabled("完成 TODO 后删除此注解")
    void practice3_3_仅按薪资范围() {
        List<Employee> r = search(null, null, new BigDecimal("15000"), new BigDecimal("20000"));
        // 15000~20000：李技术 18000、孙HR 15000、周设计 16000 → 3 人
        assertThat(r).hasSize(3);
    }

    @Test
    @Disabled("完成 TODO 后删除此注解")
    void practice3_4_全部条件为空_返回全部() {
        List<Employee> r = search(null, null, null, null);
        // data.sql 共 8 行
        assertThat(r).hasSize(8);
    }
}
