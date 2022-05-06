package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.EmployeeDAO;
import com.hspedu.mhl.domain.Employee;

/**
 *
 * 该类完成对employee表的各种操作（通过调用employeeDAO对象完成）
 */
public class EmployeeService {

    // 定义一个EmployeeDAO 属性
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    // 方法，根据empId 和 pwd 返回一个Employee对象
    // 如果查询不到，返回null
    public Employee getEmployeeByIdAndPwd(String empId, String pwd) {

        return employeeDAO.querySingle("SELECT * FROM employees WHERE empId = ? AND pwd = MD5(?)",
                Employee.class, empId, pwd);
    }
}
