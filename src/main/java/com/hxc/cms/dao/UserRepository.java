package com.hxc.cms.dao;

import com.hxc.cms.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo,Integer> {
    
}
