package com.tw;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private Connection conn = null;
    private PreparedStatement pstmt =null;

    public EmployeeDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public boolean doCreate(Employee employee) throws Exception {
        boolean flag = false;
        String sql = "INSERT INTO employee(empno, name, job, hiredate, sal) VALUES(?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, employee.getEmpno());
        this.pstmt.setString(2, employee.getName());
        this.pstmt.setString(3, employee.getJob());
        this.pstmt.setDate(4, new Date(employee.getHiredate().getTime()));
        this.pstmt.setFloat(5, employee.getSal());
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
        }

        this.pstmt.close();
        return flag;
    }

    public List<Employee> findAll(String keyword) throws Exception {
        List<Employee> all = new ArrayList<Employee>();
        String sql = "SELECT * FROM employee";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            Employee employee = new Employee();
            employee.setEmpno(rs.getInt(1));
            employee.setName(rs.getString(2));
            employee.setJob(rs.getString(3));
            employee.setHiredate(rs.getDate(4));
            employee.setSal(rs.getFloat(5));
            all.add(employee);
        }
        this.pstmt.close();
        return all;

    }

    public Employee findById(int employeeNo) throws Exception {
        Employee employee = null;
        String sql = "SELECT * FROM employee WHERE empno=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, employeeNo);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            employee = new Employee();
            employee.setEmpno(rs.getInt(1));
            employee.setName(rs.getString(2));
            employee.setJob(rs.getString(3));
            employee.setHiredate(rs.getDate(4));
            employee.setSal(rs.getFloat(5));
        }
        this.pstmt.close();
        return employee;
    }
}
