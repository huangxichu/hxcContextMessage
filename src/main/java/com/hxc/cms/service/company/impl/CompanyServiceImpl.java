package com.hxc.cms.service.company.impl;

import com.hxc.cms.dao.CompanyRepository;
import com.hxc.cms.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;



}
