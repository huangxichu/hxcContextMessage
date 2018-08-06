package com.hxc.cms.controller.department;


import com.hxc.cms.annotation.CheckLogin;
import com.hxc.cms.aspect.TokenAspect;
import com.hxc.cms.dto.Result;
import com.hxc.cms.model.Department;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.service.dept.DepartmentService;
import com.hxc.cms.service.user.UserService;
import com.hxc.cms.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UserService userService;
   
    @CheckLogin
    @GetMapping("/departmentes")
    public Result getDepartmentes(HttpServletRequest request){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        Department departmentParam = new Department();
        departmentParam.setCompanyCode(user.getCompanyCode());
        List<Department> departmentes = departmentService.findDepartmentes(departmentParam);
        return ResultUtil.success(departmentes);
    }
    
    
    @CheckLogin
    @PostMapping("/department/save")
    public Result saveDepartment(HttpServletRequest request,Department department){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        department.setCompanyCode(user.getCompanyCode());
        departmentService.save(department);
        return ResultUtil.success(department);
    }
    
    @CheckLogin
    @PutMapping("/department/update")
    public Result update(HttpServletRequest request,Department department){
        departmentService.update(department);
        return ResultUtil.success(department);
    }
    
    @CheckLogin
    @DeleteMapping("/department/delete")
    public Result delete(HttpServletRequest request,Department department){
        departmentService.delete(department.getId());
        return ResultUtil.success(department);
    }
    
    @CheckLogin
    @DeleteMapping("/department/deletes")
    public Result deletes(HttpServletRequest request,List<Integer> ids){
        departmentService.deletes(ids);
        return ResultUtil.success(ids);
    }
}
