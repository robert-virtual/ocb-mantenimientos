package com.example.ocbmantenimientos.repository;

import com.example.ocbmantenimientos.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<Table,Long> {
    List<Table> findByAppId(int appId);
}
