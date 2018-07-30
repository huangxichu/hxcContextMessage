package com.hxc.cms.service.dept.impl;

import com.hxc.cms.dao.DepartmentRepository;
import com.hxc.cms.service.dept.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


}
