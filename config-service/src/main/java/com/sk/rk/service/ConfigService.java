package com.sk.rk.service;

import com.sk.rk.model.ConfigResponse;
import com.sk.rk.model.PropertySource;
import com.sk.rk.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConfigService {

    @Autowired
    PropertyRepository propertyRepository;

    public ConfigResponse getConfigResponse(String application, String profile) {

        List<Map<String, Object>>  properties = propertyRepository.getPropertyesByApplicaitonAndProfile(application, profile);
        return createConfigResponseObject(application, profile, null, properties);
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
}
