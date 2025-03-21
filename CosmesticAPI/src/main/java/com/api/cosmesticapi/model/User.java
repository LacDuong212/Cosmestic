package com.api.cosmesticapi.model;

import com.phatbee.backendmopr.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "[User]")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long userId;
    @Column(unique = true)
    protected String username;
    protected String password;
    @Column(unique = true)
    protected String email;
    @Enumerated(EnumType.STRING)
    protected Gender gender;
    protected String image;

}
