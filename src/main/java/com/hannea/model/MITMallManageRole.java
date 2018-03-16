package com.hannea.model;

import java.io.Serializable;

/**
 * Class
 *
 * @author wgm
 * @date 2017/12/27
 */
public class MITMallManageRole implements Serializable{

    private static final long serialVersionUID = 4342607214430852206L;

    private long roleId;
    private long mallId;
    private String roleName;
    private String roleDescription;
    private int roleStatus;
    private String roleCreateBy;

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getMallId() {
        return mallId;
    }

    public void setMallId(long mallId) {
        this.mallId = mallId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public int getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(int roleStatus) {
        this.roleStatus = roleStatus;
    }

    public String getRoleCreateBy() {
        return roleCreateBy;
    }

    public void setRoleCreateBy(String roleCreateBy) {
        this.roleCreateBy = roleCreateBy;
    }
}
