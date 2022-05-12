package com.example.spring2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_data")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idUser;
    private String name;
    private String address;
    private String mail;
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<Role> roles;
    @ManyToOne
    @JoinColumn(name = "role_od")
    private Role role;

}
