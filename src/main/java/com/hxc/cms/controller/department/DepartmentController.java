package com.hxc.cms.controller.department;


import com.hxc.cms.annotation.CheckLogin;
import com.hxc.cms.aspect.TokenAspect;
import com.hxc.cms.dto.Result;
import com.hxc.cms.model.Department;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.service.dept.DepartmentService;
import com.hxc.cms.service.user.UserService;
import com.hxc.cms.utils.Constant;
import com.hxc.cms.utils.ObjectUtil;
import com.hxc.cms.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @GetMapping("/department/page")
    public Result getDepartmentes(HttpServletRequest request,
                                  @RequestParam(value = "page",required = true) Integer page,
                                  @RequestParam(value = "rows",required = true) Integer rows,
                                  @RequestParam(value = "deptName",required = false) String deptName,
                                  @RequestParam(value = "status",required = false) String status
    ){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        Department departmentParam = new Department();
        departmentParam.setCompanyCode(user.getCompanyCode());
        if(ObjectUtil.isNotBlank(deptName)){
            departmentParam.setDeptName(deptName);
        }
        if(ObjectUtil.isNotBlank(status)){
            departmentParam.setStatus(status);
        }
        PageParam pageParam = new PageParam(ObjectUtil.numberFormat(page, Constant.PAGES),ObjectUtil.numberFormat(rows,Constant.ROWS));
        Page<Department> categories = departmentService.findDepartmentsByPage(departmentParam,pageParam);
        return ResultUtil.success(categories);
    }
    
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
