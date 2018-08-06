package com.hxc.cms.controller.employee;


import com.hxc.cms.annotation.CheckLogin;
import com.hxc.cms.aspect.TokenAspect;
import com.hxc.cms.dto.Result;
import com.hxc.cms.model.Employee;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.service.employee.EmployeeService;
import com.hxc.cms.service.user.UserService;
import com.hxc.cms.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;
   
    @CheckLogin
    @GetMapping("/employees")
    public Result getEmployees(HttpServletRequest request){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        Employee employeeParam = new Employee();
        employeeParam.setCompanyCode(user.getCompanyCode());
        List<Employee> employees = employeeService.findEmployees(employeeParam);
        return ResultUtil.success(employees);
    }
    
    
    @CheckLogin
    @PostMapping("/employee/save")
    public Result saveEmployee(HttpServletRequest request,Employee employee){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        employee.setCompanyCode(user.getCompanyCode());
        employeeService.save(employee);
        return ResultUtil.success(employee);
    }
    
    @CheckLogin
    @PutMapping("/employee/update")
    public Result update(HttpServletRequest request,Employee employee){
        employeeService.update (employee);
        return ResultUtil.success(employee);
    }
    
    @CheckLogin
    @DeleteMapping("/employee/delete")
    public Result delete(HttpServletRequest request,Employee employee){
        employeeService.delete(employee.getId());
        return ResultUtil.success(employee);
    }
    
    @CheckLogin
    @DeleteMapping("/employee/deletes")
    public Result deletes(HttpServletRequest request,List<Integer> ids){
        employeeService.deletes(ids);
        return ResultUtil.success(ids);
    }
}
