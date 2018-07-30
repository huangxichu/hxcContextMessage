package com.hxc.cms.dao;

import com.hxc.cms.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    Page<Message> findAll(Specification<Message> spc, Pageable pageable);
}
