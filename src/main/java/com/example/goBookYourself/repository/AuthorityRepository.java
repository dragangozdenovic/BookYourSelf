package com.example.goBookYourself.repository;

import com.example.goBookYourself.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Integer> {

    Authority findByName(String name);
}
