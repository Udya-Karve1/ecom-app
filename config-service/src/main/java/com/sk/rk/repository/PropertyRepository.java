package com.sk.rk.repository;

import com.sk.rk.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {


    @Query(value = "select PR.Id, PR.Key, PR.Value, AP.Application, PRO.Profile from Properties PR " +
            "inner join Application AP on AP.ApplicationId = PR.ApplicationId " +
            "inner join Profile PRO on PRO.ProfileId = PR.ProfileId " +
            "where AP.ApplicationId =:ApplicationId AND PRO.ProfileId =:ProfileId " +
            "and PR.Key=:Key and PR.Value =:Value order by Profile, Application", nativeQuery = true)
    List<Map<String, Object>> getProperties(
            @Param("Key") String key,
            @Param("Value") String value,
            @Param("ApplicationId") Long applicationId,
            @Param("ProfileId") Long profileId
    );

    @Query(value = "select PR.Id, PR.Key, PR.Value, AP.Application, PRO.Profile from Properties PR " +
            "inner join Application AP on AP.ApplicationId = PR.ApplicationId " +
            "inner join Profile PRO on PRO.ProfileId = PR.ProfileId " +
            "where AP.Application = :Application AND PRO.Profile = :Profile " +
            "order by Profile, Application", nativeQuery = true)
    List<Map<String, Object>> getPropertyesByApplicaitonAndProfile(
            @Param("Application") String application,
            @Param("Profile") String profileI
    );


    @Query(value = "select PR.Id, PR.Key, PR.Value, AP.Application, PRO.Profile, " +
            "AP.ApplicationId, PR.ProfileId from Properties PR " +
            "inner join Application AP on AP.ApplicationId = PR.ApplicationId " +
            "inner join Profile PRO on PRO.ProfileId = PR.ProfileId " +
            "order by Profile, Application", nativeQuery = true)
    List<Map<String, Object>> serachProperties();


    @Query(value = "select PR.Id, PR.Key, PR.Value, AP.Application, PRO.Profile from Properties PR " +
            "inner join Application AP on AP.ApplicationId = PR.ApplicationId " +
            "inner join Profile PRO on PRO.ProfileId = PR.ProfileId " +
            "where AP.Application = :Application AND PRO.Profile = :Profile AND PRO.Label=:Label" +
            "order by Profile, Application", nativeQuery = true)
    List<Map<String, Object>> getPropertyesByApplicaitonAndProfileAndLabel(
            @Param("Application") String application,
            @Param("Profile") String profile,
            @Param("Label") String label
    );


    @Query(value = "select id, `key`, `value`, applicationid, profileid, label from Properties " +
            "where ApplicationId=:ApplicationId and ProfileId = :ProfileId and " +
            "`key` =:Key and `Value`=:Value " +
            "and id <> :Id ", nativeQuery = true)
    List<Map<String, Object>> getPropertyToValidateUpdate(
            @Param("Id") Long id,
            @Param("Key") String key,
            @Param("Value") String value,
            @Param("ApplicationId") Long applicationId,
            @Param("ProfileId") Long profileId
    );

    List<Property> findByProfileId(Long profileId);

    List<Property> findByApplicationId(Long applicationId);

}
