package com.hxc.cms.controller.company;


import com.hxc.cms.annotation.CheckLogin;
import com.hxc.cms.aspect.TokenAspect;
import com.hxc.cms.dto.Result;
import com.hxc.cms.model.Company;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.service.company.CompanyService;
import com.hxc.cms.service.user.UserService;
import com.hxc.cms.utils.ObjectUtil;
import com.hxc.cms.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;
   
    @CheckLogin
    @GetMapping("/companies")
    public Result getCompanies(HttpServletRequest request){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        Company companyParam = new Company();
        companyParam.setCompanyCode(user.getCompanyCode());
        List<Company> employees = companyService.findCompanies(companyParam);
        return ResultUtil.success(employees);
    }
    
    
    @CheckLogin
    @PostMapping("/company/save")
    public Result saveCompany(HttpServletRequest request,Company company){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
//        company.setCompanyCode(user.getCompanyCode());
        company.setCompanyCode(ObjectUtil.createRandomString(16));
        companyService.save(company);
        return ResultUtil.success(company);
    }
    
    @CheckLogin
    @PutMapping("/company/update")
    public Result update(HttpServletRequest request,Company company){
        companyService.update(company);
        return ResultUtil.success(company);
    }
    
    @CheckLogin
    @DeleteMapping("/company/delete")
    public Result delete(HttpServletRequest request,Company company){
        companyService.delete(company.getId());
        return ResultUtil.success(company);
    }
    
    @CheckLogin
    @DeleteMapping("/company/deletes")
    public Result deletes(HttpServletRequest request,List<Integer> ids){
        companyService.deletes(ids);
        return ResultUtil.success(ids);
    }
}
