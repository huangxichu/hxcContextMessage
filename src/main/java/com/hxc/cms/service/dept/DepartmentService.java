package com.hxc.cms.service.dept;

import com.hxc.cms.model.Department;
import com.hxc.cms.param.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DepartmentService {
    
    Page<Department> findDepartmentsByPage(Department departmentParam, PageParam pageParam);
    
    List<Department> findDepartmentes(Department departmentParam);
    
    void save(Department department);
    
    void update(Department department);
    
    void delete(Integer id);
    
    void deletes(List<Integer> ids);
    
}
