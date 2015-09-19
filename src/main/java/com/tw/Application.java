package com.tw;

import java.util.Date;

public class Application {
    public static void main(String[] args) throws Exception {
        Employee employee = null;
        for (int i = 0; i < 5; i++) {
            employee = new Employee();
            employee.setEmpno(1000 + i);
            employee.setName("name-" + i);
            employee.setJob("dev-" + i);
            employee.setHiredate(new Date());
            employee.setSal(500 * i);
            DAOFactory.getEmployeeDAOInstance().doCreate(employee);
        }
    }
}
