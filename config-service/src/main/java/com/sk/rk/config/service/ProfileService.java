package com.sk.rk.config.service;


import com.sk.rk.common.exception.BaseException;
import com.sk.rk.config.model.Profile;
import com.sk.rk.config.model.Property;
import com.sk.rk.config.repository.ProfileRepository;
import com.sk.rk.config.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    PropertyRepository propertyRepository;

    public List<Map<String, Object>> getAllProfiles() {

        return profileRepository.getAllProfile();
    }


    public Profile saveProfile(Profile profile) throws BaseException {
        List<Profile> applications = profileRepository
                .findByProfileNameIgnoreCase(profile.getProfileName());

        if(CollectionUtils.isEmpty(applications)) {
            return profileRepository.save(profile);
        } else {
            throw new BaseException(400, "Profile already exists with name : " + profile.getProfileName());
        }
    }


    public Profile updateProfile(Profile profile) throws BaseException {
        List<Profile> profileList = profileRepository.getProfileValidateUpdate(profile.getProfileId(), profile.getProfileName());
        if(CollectionUtils.isEmpty(profileList)) {
            return profileRepository.saveAndFlush(profile);
        } else {
            throw new BaseException(400, "Profile already exists with name : " + profile.getProfileName());
        }
    }


    public void deleteProfile(Long profileId) throws BaseException {

        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(()->new BaseException(400, "Profile not found."));
        List<Property> properties = propertyRepository.findByProfile(profile);

        if(!CollectionUtils.isEmpty(properties)){
            throw new BaseException(400, "Can not delete Profile, profile is mapped with property");
        }

        profileRepository.delete(profile);
    }
}
