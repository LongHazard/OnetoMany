package com.example.spring2.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "role_data")
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String roleName;
//    @ManyToOne
//    @JoinColumn(name = "idUser")
//    private User user;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<User>();
}
