package com.tw;

import java.util.List;

public class EmployeeDAOProxy implements EmployeeDAO {
    private DatabaseConnection dbc = null;
    private EmployeeDAOImpl dao = null;

    public EmployeeDAOProxy() throws Exception {
        this.dbc = new DatabaseConnection();
        this.dao = new EmployeeDAOImpl(this.dbc.getConnection());
    }

    public boolean doCreate(Employee employee) throws Exception {
        boolean flag = false;
        try {
            if (this.dao.findById(employee.getEmpno()) == null) {
                flag = this.dao.doCreate(employee);
            }
            return flag;
        } finally {
            this.dbc.close();
        }
    }

    public List<Employee> findAll(String keyword) throws Exception {
        try {
            List<Employee> all = this.dao.findAll(keyword);
            return all;
        } finally {
            this.dbc.close();
        }
    }

    public Employee findById(int employeeNo) throws Exception {
        try {
            Employee employee = null;
            employee = this.dao.findById(employeeNo);
            return employee;
        } finally {
            this.dbc.close();
        }
    }
}
