package com.nexthink.ContentGenerator;

import java.util.List;

public class ProfilePojo {
    private String name;
    private String description;
    private List<Permission> permissions;
    private Integer entityVersion;
    private String[] roleIds;
    private List<ViewDomain> viewDomains;
    private String timezone;

    public ProfilePojo(String name, String description, List<Permission> permissions, Integer entityVersion, String[] roleIds, List<ViewDomain> viewDomains, String timezone) {
        this.name = name;
        this.description = description;
        this.permissions = permissions;
        this.entityVersion = entityVersion;
        this.roleIds = roleIds;
        this.viewDomains = viewDomains;
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Permission> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
    public Integer getEntityVersion() {
        return entityVersion;
    }
    public void setEntityVersion(Integer entityVersion) {
        this.entityVersion = entityVersion;
    }
    public String[] getRoleIds() {
        return roleIds;
    }
    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }
    public List<ViewDomain> getViewDomains() {
        return viewDomains;
    }
    public void setViewDomains(List<ViewDomain> viewDomains) {
        this.viewDomains = viewDomains;
    }
    public String getTimezone() {
        return timezone;
    }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}