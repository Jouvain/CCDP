package com.ccdp.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccdp.main.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
