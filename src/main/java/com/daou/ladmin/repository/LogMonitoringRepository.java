package com.daou.ladmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daou.ladmin.entity.LogMonitoring;
import com.daou.ladmin.entity.LogMonitoringId;

@Repository
public interface LogMonitoringRepository extends JpaRepository<LogMonitoring, LogMonitoringId> {

}
