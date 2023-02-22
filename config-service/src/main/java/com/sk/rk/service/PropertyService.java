package com.sk.rk.service;

import com.sk.rk.model.Application;
import com.sk.rk.model.Profile;
import com.sk.rk.model.Property;
import com.sk.rk.repository.ApplicationRepository;
import com.sk.rk.repository.ProfileRepository;
import com.sk.rk.repository.PropertyRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.sk.rk.model.PropertyAddRequest;
import com.sk.rk.model.PropertyUpdateRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ProfileRepository profileRepository;

    private static List<String> profileList = new ArrayList<>();

    @PostConstruct
    public void init() {
        profileList.add("dev");
        profileList.add("test");
        profileList.add("stag");
        profileList.add("pre-production");
        profileList.add("production");
    }

    public List<Map<String, Object>> getAllProperties() {
        return propertyRepository.serachProperties();
    }

    public Map<String, Object> getPropertyById(Long propertyId) throws Exception {
        return propertyRepository.getPropertyById(propertyId).stream().findFirst().orElseThrow(()-> new Exception("Property not found"));
    }


    private Property createEntityProperty(Application application, Profile profile, PropertyAddRequest request ){
        Property property = new Property();
        property.setApplication(application);
        property.setProfile(profile);
        property.setKey(request.getKey());
        property.setValue(request.getValue());
        property.setLabel(request.getLabel());

        return property;
    }

    private Property createEntityPropertyEdit(Application application, Profile profile, PropertyUpdateRequest  request ){
        Property property = createEntityProperty(application, profile, request);
        property.setId(request.getId());

        return property;
    }


    public Property saveProperty(PropertyAddRequest request) throws Exception {

        Profile profile = profileRepository.findById(request.getProfileId()).orElseThrow(()-> new Exception("Profile not found"));
        Application application = applicationRepository.findById(request.getApplicationId()).orElseThrow(()-> new Exception("ApplicationRepository not found"));


        List<Map<String, Object>> propertyList = propertyRepository.getProperties(
                request.getKey(), request.getValue(), request.getApplicationId(), request.getProfileId()
        );


        if(CollectionUtils.isEmpty(propertyList)) {
            return propertyRepository.save(createEntityProperty(application, profile, request));
        } else {

            Map<String, Object> propertyMap = propertyList.get(0);

            throw new Exception (
                    "Property already exists \n" + "Key: " + propertyMap.get("Key") + "\n" +
                    "Value: " + propertyMap.get("Value") + "\n" +
                    "Application: " + propertyMap.get("Application") + "\n" +
                    "Profile: " + propertyMap.get("Profile") + "\n" +
                    "Label: " + propertyMap.get("Label") + "\n" );
        }
    }




    public Property updateProperty(PropertyUpdateRequest request) throws Exception {

        Profile profile = profileRepository.findById(request.getProfileId()).orElseThrow(()-> new Exception("Profile not found"));
        Application application = applicationRepository.findById(request.getApplicationId()).orElseThrow(()-> new Exception("ApplicationRepository not found"));

        List<Map<String, Object>> propertyList = propertyRepository.getPropertyToValidateUpdate(
                request.getId(), request.getKey(), request.getValue(), request.getApplicationId(), request.getProfileId()
                );

        if(CollectionUtils.isEmpty(propertyList)) {
            return propertyRepository.save(createEntityPropertyEdit(application, profile, request));
        } else {
            Map<String, Object> propertyMap = propertyList.get(0);

            throw new Exception (
                    "Property already exists \n" + "Key: " + propertyMap.get("Key") + "\n" +
                            "Value: " + propertyMap.get("Value") + "\n" +
                            "Application: " + propertyMap.get("Application") + "\n" +
                            "Profile: " + propertyMap.get("Profile") + "\n" +
                            "Label: " + propertyMap.get("Label") + "\n" );
        }
    }


    public List<String> getAllProfile() {
        return profileList;
    }

    @Transactional
    public void deleteProperty(Long propertyId) throws Exception {
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
        if(propertyOptional.isPresent()) {
            propertyRepository.delete(propertyOptional.get());
        } else {
            throw new Exception("Property not found.");
        }
    }
}
