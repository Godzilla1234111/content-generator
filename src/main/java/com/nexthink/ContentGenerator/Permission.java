package com.nexthink.ContentGenerator;

public class Permission  {
    private Integer permissionId;
    private String value;

    public Permission(Integer permissionId, String value) {
        this.permissionId = permissionId;
        this.value = value;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}