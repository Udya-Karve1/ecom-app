package com.sk.rk.service;


import com.sk.rk.model.Profile;
import com.sk.rk.model.Property;
import com.sk.rk.repository.ProfileRepository;
import com.sk.rk.repository.PropertyRepository;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    PropertyRepository propertyRepository;

    public List<Map<String, Object>> getAllProfiles() {

        return profileRepository.getAllProfile();
    }


    public Profile saveProfile(Profile profile) throws Exception {
        List<Profile> applications = profileRepository
                .findByProfileIgnoreCase(profile.getProfile());

        if(CollectionUtils.isEmpty(applications)) {
            return profileRepository.save(profile);
        } else {
            throw new Exception("Profile already exists with name : " + profile.getProfile());
        }
    }


    public Profile updateProfile(Profile profile) throws Exception {
        List<Profile> profileList = profileRepository.getProfileValidateUpdate(profile.getProfileId(), profile.getProfile());
        if(CollectionUtils.isEmpty(profileList)) {
            return profileRepository.saveAndFlush(profile);
        } else {
            throw new Exception("Profile already exists with name : " + profile.getProfile());
        }
    }


    @Transient
    public void deleteProfile(Long profileId) throws Exception {

        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(()->new Exception("Profile not found."));
        List<Property> properties = propertyRepository.findByProfile(profile);

        if(!CollectionUtils.isEmpty(properties)){
            throw new Exception("Can not delete Profile, profile is mapped with property");
        }

        profileRepository.delete(profile);
    }
}
