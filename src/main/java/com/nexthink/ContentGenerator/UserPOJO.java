package com.nexthink.ContentGenerator;

import java.util.List;

public class UserPOJO {

    private List<Object> additionalRoles;

    private String email;

    private int entityVersion;

    private String fullname;

    private String password;

    private String passwordConfirmation;

    private String profileId;

    private String[] profileParams;

    private boolean unlimitedSession;

    private String username;


    public List<Object> getAdditionalRoles() {
        return additionalRoles;
    }

    public void setAdditionalRoles(List<Object> additionalRoles) {
        this.additionalRoles = additionalRoles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEntityVersion() {
        return entityVersion;
    }

    public void setEntityVersion(int entityVersion) {
        this.entityVersion = entityVersion;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String[] getProfileParams() {
        return profileParams;
    }

    public void setProfileParams(String[] profileParams) {
        this.profileParams = profileParams;
    }

    public boolean isUnlimitedSession() {
        return unlimitedSession;
    }

    public void setUnlimitedSession(boolean unlimitedSession) {
        this.unlimitedSession = unlimitedSession;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserPOJO(List<Object> additionalRoles, String email, int entityVersion, String fullname, String password, String passwordConfirmation, String profileId, String[] profileParams, boolean unlimitedSession, String username) {
        this.additionalRoles = additionalRoles;
        this.email = email;
        this.entityVersion = entityVersion;
        this.fullname = fullname;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.profileId = profileId;
        this.profileParams = profileParams;
        this.unlimitedSession = unlimitedSession;
        this.username = username;
    }
}