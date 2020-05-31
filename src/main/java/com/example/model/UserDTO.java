package com.example.model;

public class UserDTO {
    private Long id;
    private String login;

    public UserDTO() {
    }

    private UserDTO(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public static UserDTO of(Long id, String login) {
        return new UserDTO(id, login);
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
