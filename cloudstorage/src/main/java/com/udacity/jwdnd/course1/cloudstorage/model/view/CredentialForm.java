package com.udacity.jwdnd.course1.cloudstorage.model.view;

public class CredentialForm {
    private String url;
    private String username;
    private String password;
    private int credentialId;

    public CredentialForm(String url, String username, String password, int credentialId) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.credentialId = credentialId;
    }

    public CredentialForm() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(int credentialId) {
        this.credentialId = credentialId;
    }
}
