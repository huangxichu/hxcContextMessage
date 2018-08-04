package com.hxc.cms.service.employee.impl;

import com.hxc.cms.dao.EmployeeRepository;
import com.hxc.cms.model.Employee;
import com.hxc.cms.model.News;
import com.hxc.cms.service.employee.EmployeeService;
import com.hxc.cms.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> findEmployees(Employee employeeParam) {
        Example<Employee> example = Example.of(employeeParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime","updateTime")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(employeeParam, exampleMatcher);
        return this.employeeRepository.findAll(example);
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void update(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if(ObjectUtil.isNotBlank(id)){
            this.employeeRepository.delete(id);
        }
    }

    @Override
    @Transactional
    public void deletes(List<Integer> ids) {
        if(ObjectUtil.isNotBlank(ids)){
            this.employeeRepository.deleteByIds(ids);
        }
    }
}
