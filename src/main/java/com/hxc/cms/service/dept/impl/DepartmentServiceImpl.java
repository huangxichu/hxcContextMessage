package com.hxc.cms.service.dept.impl;

import com.hxc.cms.dao.DepartmentRepository;
import com.hxc.cms.model.Department;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.service.dept.DepartmentService;
import com.hxc.cms.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Override
    public Page<Department> findDepartmentsByPage(Department departmentParam, PageParam pageParam) {
        Example<Department> example = Example.of(departmentParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(departmentParam, exampleMatcher);
        Pageable pageable = new PageRequest(pageParam.getPage()-1,pageParam.getRows()); //页码：前端从1开始，jpa从0开始，做个转换
        return this.departmentRepository.findAll(example,pageable);
    }
    
    @Override
    public List<Department> findDepartmentes(Department departmentParam) {
        Example<Department> example = Example.of(departmentParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(departmentParam, exampleMatcher);
        return this.departmentRepository.findAll(example);
    }
    
    @Override
    @Transactional
    public void save(Department department) {
        this.departmentRepository.save(department);
    }
    
    @Override
    @Transactional
    public void update(Department department) {
        this.departmentRepository.save(department);
    }
    
    @Override
    @Transactional
    public void delete(Integer id) {
        if(ObjectUtil.isNotBlank(id)){
            this.departmentRepository.delete(id);
        }
    }
    
    @Override
    @Transactional
    public void deletes(List<Integer> ids) {
        if(ObjectUtil.isNotBlank(ids)){
            this.departmentRepository.deleteByIds(ids);
        }
    }
}
