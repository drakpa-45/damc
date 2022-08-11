package com.ngn.spring.project.auth;

/**
 * ==================================================================================
 * Created by Pema drakpa
 * Created date: 29-Nov-21
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class UserRoleDTO {
    private String userId;
    private Integer roleId;
    private String roleName;
    private Integer roleRefNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleRefNo() {
        return roleRefNo;
    }

    public void setRoleRefNo(Integer roleRefNo) {
        this.roleRefNo = roleRefNo;
    }
}
