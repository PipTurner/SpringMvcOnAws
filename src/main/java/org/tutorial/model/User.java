package org.tutorial.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class User {

    @NotEmpty(message = "{NotEmpty}")
    @Email(message = "{Email}")
    private String username;

    @Size(min = 8, message = "{Size}")
    private String password;

    @NotEmpty(message = "{NotEmpty}")
    private String passwordConfirm;

    private Short enabled;
    private String[] roles;

    public User() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return this.passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Short getEnabled() {
        return enabled;
    }

    public void setEnabled(Short enabled) {
        this.enabled = enabled;
    }

    public List<String> getRoles() {

        List<String> authorities = new ArrayList<String>();
        for (String r:roles) {
            authorities.add(r);
        }
        return authorities;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
