package com.hxc.cms.service.company;

import com.hxc.cms.model.Company;
import com.hxc.cms.param.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {
    
    Page<Company> findCompaniesByPage(Company companyParam, PageParam pageParam);
    
    List<Company> findCompanies(Company companyParam);
    
    void save(Company company);
    
    void update(Company company);
    
    void delete(Integer id);
    
    void deletes(List<Integer> ids);
    
}
