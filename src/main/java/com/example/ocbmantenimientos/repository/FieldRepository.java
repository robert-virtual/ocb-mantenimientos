package com.example.ocbmantenimientos.repository;

import com.example.ocbmantenimientos.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field,Long> {
    List<Field> findByTableId(long tableId);
}
