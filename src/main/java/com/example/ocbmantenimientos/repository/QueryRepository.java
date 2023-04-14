package com.example.ocbmantenimientos.repository;

import com.example.ocbmantenimientos.model.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueryRepository extends JpaRepository<Query, Long> {
    List<Query> findAllByStatusOrderByRequestedAt(String status, Pageable pageable);

}
