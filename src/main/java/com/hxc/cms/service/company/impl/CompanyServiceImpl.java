package com.hxc.cms.service.company.impl;

import com.hxc.cms.dao.CompanyRepository;
import com.hxc.cms.model.Company;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.service.company.CompanyService;
import com.hxc.cms.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    
    @Override
    public Page<Company> findCompaniesByPage(Company companyParam, PageParam pageParam) {
        Example<Company> example = Example.of(companyParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(companyParam, exampleMatcher);
        Pageable pageable = new PageRequest(pageParam.getPage()-1,pageParam.getRows()); //页码：前端从1开始，jpa从0开始，做个转换
        return this.companyRepository.findAll(example,pageable);
    }
    
    @Override
    public List<Company> findCompanies(Company companyParam) {
        Example<Company> example = Example.of(companyParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(companyParam, exampleMatcher);
        return this.companyRepository.findAll(example);
    }
    
    @Override
    public void save(Company company) {
        this.companyRepository.save(company);
    }
    
    @Override
    public void update(Company company) {
        this.companyRepository.save(company);
    }
    
    @Override
    public void delete(Integer id) {
        if(ObjectUtil.isNotBlank(id)){
            this.companyRepository.delete(id);
        }
    }
    
    @Override
    public void deletes(List<Integer> ids) {
        if(ObjectUtil.isNotBlank(ids)){
            this.companyRepository.deleteByIds(ids);
        }
    }
}
