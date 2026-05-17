package com.hbnu.course02.practice;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hbnu.course02.practice.entity.Employee;
import com.hbnu.course02.practice.mapper.EmployeeMapper;
import com.hbnu.course02.practice.practice2.BatchUpdateLegacyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Practice2：重构批量更新（重构改造题）
 * 对应课件 3.2 节 "update 的两种重载"
 *
 * <h3>练习要求</h3>
 * 打开 {@link BatchUpdateLegacyService} 看代码 —— 它先 selectList 查出所有目标，再循环 updateById。
 * <p>请重构该 service 的 {@code renameDepartment} 方法，改用 <b>一次</b>
 * {@code empMapper.update(null, Wrappers.<Employee>lambdaUpdate()...)} 调用完成。
 *
 * <p>本测试的断言**重构前和重构后都应通过**（因为只断言行为正确性）。
 * 重构成功的判定标准：观察 SQL 日志 —— 从 "SELECT + N 次 UPDATE" 变成 "1 次 UPDATE"。
 */
@SpringBootTest
@Transactional
class Practice2_RefactorBatchUpdateTest {

    @Autowired
    private BatchUpdateLegacyService legacyService;

    @Autowired
    private EmployeeMapper empMapper;

    @Test
    void practice2_行为正确性_重构前后都应通过() {
        // 调用业务方法：把"技术部"全员改成"研发部"
        int rows = legacyService.renameDepartment("技术部", "研发部");

        // 断言①：data.sql 里"技术部"有 3 人 -> 应改 3 行
        assertThat(rows).isEqualTo(3);

        // 断言②：操作后再查"技术部"应该 0 人
        Long stillTech = empMapper.selectCount(
                Wrappers.<Employee>lambdaQuery().eq(Employee::getDepartment, "技术部"));
        assertThat(stillTech).isEqualTo(0L);

        // 断言③："研发部"应该 3 人
        Long newDept = empMapper.selectCount(
                Wrappers.<Employee>lambdaQuery().eq(Employee::getDepartment, "研发部"));
        assertThat(newDept).isEqualTo(3L);

        System.out.println("【重构前 vs 重构后】请观察上方 SQL 日志：");
        System.out.println("  - 重构前：1 条 SELECT + 3 条 UPDATE");
        System.out.println("  - 重构后：1 条 UPDATE");
    }
}
