package com.sk.rk.service;

import com.sk.rk.model.Property;
import com.sk.rk.repository.PropertyRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository propertyRepository;


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

    public Property saveProperty(Property propertyEntity) throws Exception {

        List<Map<String, Object>> propertyList = propertyRepository.getProperties(
                propertyEntity.getKey(), propertyEntity.getValue(), propertyEntity.getApplicationId(), propertyEntity.getProfileId()
        );

        if(CollectionUtils.isEmpty(propertyList)) {
            return propertyRepository.save(propertyEntity);
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


    public Property updateProperty(Property propertyEntity) throws Exception {

        List<Map<String, Object>> propertyList = propertyRepository.getPropertyToValidateUpdate(
                propertyEntity.getId(), propertyEntity.getKey(), propertyEntity.getValue(), propertyEntity.getApplicationId(), propertyEntity.getProfileId()
                );

        if(CollectionUtils.isEmpty(propertyList)) {
            return propertyRepository.saveAndFlush(propertyEntity);
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
