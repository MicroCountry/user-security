package com.hannea.model;

import java.io.Serializable;

/**
 * Class
 *
 * @author wgm
 * @date 2017/12/27
 */
public class MITMallManageUserRole implements Serializable{
    private static final long serialVersionUID = 8499998594565316421L;

    private long userRoleId;
    private long userId;
    private long roleId;
    private long mallId;
    private int userRoleStatus;
    private long userRoleCreateBy;
    private long userRoleCreateTime;

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

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

    public int getUserRoleStatus() {
        return userRoleStatus;
    }

    public void setUserRoleStatus(int userRoleStatus) {
        this.userRoleStatus = userRoleStatus;
    }

    public long getUserRoleCreateBy() {
        return userRoleCreateBy;
    }

    public void setUserRoleCreateBy(long userRoleCreateBy) {
        this.userRoleCreateBy = userRoleCreateBy;
    }

    public long getUserRoleCreateTime() {
        return userRoleCreateTime;
    }

    public void setUserRoleCreateTime(long userRoleCreateTime) {
        this.userRoleCreateTime = userRoleCreateTime;
    }
}
