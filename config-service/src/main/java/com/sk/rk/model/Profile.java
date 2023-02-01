package com.sk.rk.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Profile")
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProfileId")
    Long profileId;

    @Column(name = "Profile")
    String profile;
}
