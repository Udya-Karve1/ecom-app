package com.sk.rk.service;

import com.sk.rk.model.*;
import com.sk.rk.repository.ApplicationRepository;
import com.sk.rk.repository.ProfileRepository;
import com.sk.rk.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConfigService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public ConfigResponse getConfigResponse(String application, String profile) {

        List<Application> applications = applicationRepository.findByApplicationIgnoreCase(application);
        List<Profile> profiles = profileRepository.findByProfileIgnoreCase(profile);
        List<Property> propertyList = propertyRepository.findByApplicationAndProfile(applications.get(0), profiles.get(0));

        return createConfigResponseObject1(application.toLowerCase(), profile.toLowerCase(), null, propertyList);
    }

    public ConfigResponse getConfigResponse(String application, String profile, String label) {

        List<Map<String, Object>>  properties = propertyRepository.getPropertyesByApplicaitonAndProfileAndLabel(application, profile, label);
        return createConfigResponseObject(application, profile, label, properties);
    }

    private ConfigResponse createConfigResponseObject(String application, String profile, String label, List<Map<String, Object>> propertyList) {
        ConfigResponse configResponse = new ConfigResponse();
        configResponse.setName(application);
        configResponse.setLabel(label);
        configResponse.setProfiles(Collections.singletonList(profile));
        List<PropertySource> propertySources = new ArrayList<>();
        PropertySource propertySource = new PropertySource();
        propertySource.setName(application);
        Map<String, Object> source = new HashMap<>();

        propertyList.stream().forEach(map->{
            source.put(map.get("Key").toString(), map.get("Value"));
        });
        propertySource.setSource(source);
        propertySources.add(propertySource);
        configResponse.setPropertySources(propertySources);

        return configResponse;
    }


    private ConfigResponse createConfigResponseObject1(String application, String profile, String label, List<Property> propertyList) {
        ConfigResponse configResponse = new ConfigResponse();
        configResponse.setName(application);
        configResponse.setLabel(label);
        configResponse.setProfiles(Collections.singletonList(profile));
        List<PropertySource> propertySources = new ArrayList<>();
        PropertySource propertySource = new PropertySource();
        propertySource.setName(application);
        Map<String, Object> source = new HashMap<>();

        propertyList.stream().forEach(map->{
            source.put(map.getKey() , map.getValue());
        });
        propertySource.setSource(source);
        propertySources.add(propertySource);
        configResponse.setPropertySources(propertySources);

        return configResponse;
    }
}
