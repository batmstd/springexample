package com.example.model;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    private Long id;
    private String login;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column
    public String getLogin() {
        return login;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserEntity() {
    }

    public UserEntity(String login) {
        this.login = login;
    }
}
