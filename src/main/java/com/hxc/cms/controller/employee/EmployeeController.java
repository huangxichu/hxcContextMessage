package com.hxc.cms.controller.employee;


import com.hxc.cms.annotation.CheckLogin;
import com.hxc.cms.aspect.TokenAspect;
import com.hxc.cms.dto.Result;
import com.hxc.cms.model.Employee;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.service.employee.EmployeeService;
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
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;
    
    @CheckLogin
    @GetMapping("/employee/page")
    public Result getDepartmentes(HttpServletRequest request,
                                  @RequestParam(value = "page",required = true) Integer page,
                                  @RequestParam(value = "rows",required = true) Integer rows,
                                  @RequestParam(value = "relName",required = false) String relName,
                                  @RequestParam(value = "phone",required = false) String phone,
                                  @RequestParam(value = "sex",required = false) String sex,
                                  @RequestParam(value = "status",required = false) String status
    ){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        Employee employeeParam = new Employee();
        employeeParam.setCompanyCode(user.getCompanyCode());
        if(ObjectUtil.isNotBlank(relName)){
            employeeParam.setRelName(relName);
        }
        if(ObjectUtil.isNotBlank(sex)){
            employeeParam.setSex(sex);
        }
        if(ObjectUtil.isNotBlank(phone)){
            employeeParam.setPhone(phone);
        }
        if(ObjectUtil.isNotBlank(status)){
            employeeParam.setStatus(status);
        }
        PageParam pageParam = new PageParam(ObjectUtil.numberFormat(page, Constant.PAGES),ObjectUtil.numberFormat(rows,Constant.ROWS));
        Page<Employee> employees = employeeService.findEmployeesByPage(employeeParam,pageParam);
        return ResultUtil.success(employees);
    }
    
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
