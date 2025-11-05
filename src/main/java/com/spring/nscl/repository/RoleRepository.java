package com.spring.nscl.repository;

import com.spring.nscl.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional <Role> findByName(String name);
}
