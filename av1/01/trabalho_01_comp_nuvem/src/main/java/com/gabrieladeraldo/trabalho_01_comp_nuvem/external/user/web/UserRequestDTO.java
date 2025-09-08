package com.gabrieladeraldo.trabalho_01_comp_nuvem.external.user.web;

public class UserRequestDTO {
    private String username;
    private String email;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
