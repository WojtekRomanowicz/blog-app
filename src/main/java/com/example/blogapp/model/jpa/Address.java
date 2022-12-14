package com.example.blogapp.model.jpa;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;

    @OneToOne(mappedBy = "address")
    private User user;

}
