package com.tw;

import java.util.List;

public interface EmployeeDAO {
    boolean doCreate(Employee employee) throws Exception;
    List<Employee> findAll(String keyword) throws Exception;
    Employee findById(int employeeNo) throws Exception;
}
