package com.example.ocbmantenimientos.repository;

import com.example.ocbmantenimientos.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    List<Role> findByNameIn(List<String> names);
}
