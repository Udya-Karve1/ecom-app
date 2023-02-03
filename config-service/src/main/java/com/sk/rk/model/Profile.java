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
    @Column(name = "profile_id")
    Long profileId;

    @Column(name = "profile")
    String profile;

    @OneToMany(mappedBy = "profile")
    private List<Property> propertyList = new ArrayList<>();

}
