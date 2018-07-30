package com.hxc.cms.service.employee.impl;

import com.hxc.cms.dao.EmployeeRepository;
import com.hxc.cms.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;



}
