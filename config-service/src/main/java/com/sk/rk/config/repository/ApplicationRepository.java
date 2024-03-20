package com.sk.rk.config.repository;

import com.sk.rk.config.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByApplicationNameIgnoreCase(
            @Param("Application") String aplication
    );

    @Query(value = "select application_id as applicationId, application_name as application, '' as action " +
            "from Application order by application", nativeQuery = true)
    List<Map<String, Object>> getAllAplications();





}
