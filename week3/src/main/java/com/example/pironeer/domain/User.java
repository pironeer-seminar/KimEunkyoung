package com.example.pironeer.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String username;
    public String email;

    protected User(){}

    public User(String username, String email){
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getName(){
        return username;
    }
}
