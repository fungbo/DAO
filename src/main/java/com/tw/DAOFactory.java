package com.tw;

public class DAOFactory {
    public static EmployeeDAO getEmployeeDAOInstance() throws Exception {
        return new EmployeeDAOProxy();
    }
}
