package com.hbnu.course02.practice.practice2;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hbnu.course02.practice.entity.Employee;
import com.hbnu.course02.practice.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Practice2 重构起点 —— 这是个"完整可运行但有坏味道"的批量更新服务。
 *
 * 当前实现：先 selectList 查出所有匹配的员工，循环 N 次 updateById。
 * 每条记录一次 SELECT + 一次 UPDATE，性能极差。
 *
 * 【你的任务】
 * 重构 renameDepartment(String, String) 方法，要求：
 *   1. 用一次 update(null, wrapper) 调用完成批量更新
 *   2. 方法签名和行为不变（返回受影响行数）
 *   3. 重构前/后 Practice2_RefactorBatchUpdateTest 都应通过
 *   4. 重构后观察 SQL 日志，从 N+1 条变成 1 条 UPDATE
 *
 * 【提示】
 * 课件 3.2 节 "LambdaUpdateWrapper" + 课件 3.2 节末 "update 的两种重载"。
 */
@Service
public class BatchUpdateLegacyService {

    @Autowired
    private EmployeeMapper empMapper;

    /**
     * 把所有部门 == fromDept 的员工的部门改成 toDept。
     */
    public int renameDepartment(String fromDept, String toDept) {
        List<Employee> targets = empMapper.selectList(
                Wrappers.<Employee>lambdaQuery().eq(Employee::getDepartment, fromDept));

        int count = 0;
        for (Employee emp : targets) {
            emp.setDepartment(toDept);
            count += empMapper.updateById(emp);
        }
        return count;
    }
}
