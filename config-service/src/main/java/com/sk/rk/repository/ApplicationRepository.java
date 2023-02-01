package com.sk.rk.repository;

import com.sk.rk.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByApplicationIgnoreCase(
            @Param("Application") String aplication
    );

    @Query(value = "select applicationId as applicationId, application as application, '' as action " +
            "from Application order by Application", nativeQuery = true)
    List<Map<String, Object>> getAllAplications();



}
