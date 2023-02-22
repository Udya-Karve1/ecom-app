package com.sk.rk.repository;

import com.sk.rk.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {



    List<Profile> findByProfileIgnoreCase(
            @Param("Profile") String profile
    );

    @Query(value = "select profile_id as profileId, profile, '' as action from Profile order by profile", nativeQuery = true)
    List<Map<String, Object>> getAllProfile();

    @Query(value = "select profile_id as profileId, profile from Profile where Profile=:Profile and ProfileId<>:ProfileId ", nativeQuery = true)
    List<Profile> getProfileValidateUpdate(
            @Param("ProfileId") Long profileId,
            @Param("Profile") String profile
    );
}
