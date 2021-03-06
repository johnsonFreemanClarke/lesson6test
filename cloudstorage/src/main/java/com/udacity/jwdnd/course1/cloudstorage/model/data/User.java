package com.udacity.jwdnd.course1.cloudstorage.model.data;

import com.udacity.jwdnd.course1.cloudstorage.model.view.SignupForm;

/**
 *  userid INT PRIMARY KEY auto_increment,
 *     username VARCHAR(20),
 *     salt VARCHAR,
 *     password VARCHAR,
 *     firstname VARCHAR(20),
 *     lastname VARCHAR(20)
 */
public class User {

    private Integer userId;
    private String username;
    private String salt;
    private String password;
    private String firstName;
    private String lastName;

    public User(Integer userId, String username, String salt, String password, String firstName, String lastName) {
        this.userId = userId;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(SignupForm signupForm){
        this.username = signupForm.getUsername();
        this.password = signupForm.getPwd();
        this.firstName = signupForm.getFirstName();
        this.lastName = signupForm.getLastName();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
