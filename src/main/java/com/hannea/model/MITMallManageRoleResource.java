package com.hannea.model;

import java.io.Serializable;

/**
 * Class
 *
 * @author wgm
 * @date 2017/12/27
 */
public class MITMallManageRoleResource implements Serializable{
    private static final long serialVersionUID = -3595870116748280138L;
    private long roleResourceId;
    private long mallId;
    private long roleId;
    private long resourceId;
    private int roleResourceStatus;
    private long createBy;
    private long createTime;

    public long getRoleResourceId() {
        return roleResourceId;
    }

    public void setRoleResourceId(long roleResourceId) {
        this.roleResourceId = roleResourceId;
    }

    public long getMallId() {
        return mallId;
    }

    public void setMallId(long mallId) {
        this.mallId = mallId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    public int getRoleResourceStatus() {
        return roleResourceStatus;
    }

    public void setRoleResourceStatus(int roleResourceStatus) {
        this.roleResourceStatus = roleResourceStatus;
    }

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
