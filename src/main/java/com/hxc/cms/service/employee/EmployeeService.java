package com.hxc.cms.service.employee;

import com.hxc.cms.model.Employee;
import com.hxc.cms.model.Message;

import java.util.List;

public interface EmployeeService {

    List<Employee> findEmployees(Employee employeeParam);

    void save(Employee employee);

    void update(Employee employee);

    void delete(Integer id);

    void deletes(List<Integer> ids);

}
