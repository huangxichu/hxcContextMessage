package com.hxc.cms.service.employee;

import com.hxc.cms.model.Employee;
import com.hxc.cms.model.Message;
import com.hxc.cms.model.News;
import com.hxc.cms.param.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    
    Page<Employee> findEmployeesByPage(Employee employeeParam, PageParam pageParam);
    
    List<Employee> findEmployees(Employee employeeParam);

    void save(Employee employee);

    void update(Employee employee);

    void delete(Integer id);

    void deletes(List<Integer> ids);

}
