package com.example.ocbmantenimientos.repository;

import com.example.ocbmantenimientos.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog,Long> {
}
