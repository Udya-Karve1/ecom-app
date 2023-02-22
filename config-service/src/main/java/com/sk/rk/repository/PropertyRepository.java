package com.sk.rk.repository;

import com.sk.rk.model.Application;
import com.sk.rk.model.Profile;
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

    @Query(value = "select PR.id, PR.key, PR.value, lower(AP.application) application, PRO.profile from properties PR " +
            "inner join Application AP on AP.application_id = PR.application_id " +
            "inner join Profile PRO on PRO.profile_id = PR.profile_id " +
            "where AP.application = :Application AND PRO.profile = :Profile " +
            "order by profile, application", nativeQuery = true)
    List<Map<String, Object>> getPropertyesByApplicaitonAndProfile(
            @Param("Application") String application,
            @Param("Profile") String profileI
    );


    @Query(value = "select PR.id, PR.key, PR.value, AP.application, PRO.profile, AP.application_id, PR.profile_id from properties PR inner join Application AP on AP.application_id = PR.application_id inner join Profile PRO on PRO.profile_Id = PR.profile_id order by profile, application", nativeQuery = true)
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


    List<Property> findByApplicationAndProfile(Application application, Profile profile);

    List<Property> findByApplication(Application application);



    List<Property> findByProfile(Profile profile);


    @Query(value = "select id, `key`, `value`, application_id, profile_id, label from Properties " +
            "where id = :Id ", nativeQuery = true)
    List<Map<String, Object>> getPropertyById(
            @Param("Id") Long id
    );



}
