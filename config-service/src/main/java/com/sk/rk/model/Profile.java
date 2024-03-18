package com.sk.rk.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Profile")
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Profile_Id")
    private Long profileId;

    @Column(name = "Profile_Name")
    private String profileName;

    @OneToMany(mappedBy = "profile")
    private List<Property> propertyList = new ArrayList<>();

}
