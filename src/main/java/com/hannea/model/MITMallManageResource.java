package com.hannea.model;

import java.io.Serializable;

/**
 * Class
 *
 * @author wgm
 * @date 2017/12/27
 */
public class MITMallManageResource implements Serializable{

    private static final long serialVersionUID = -7768611661669635343L;

    private long resourceId;
    private long mallId;
    private int resourceType;
    private String resourceName;
    private int resourceStatus;
    private long resourceParentId;
    private long resourcePageId;
    private int resourceAllAllow;
    private String resourceAction;
    private String resourceCreateTime;
    private long resourceCreateBy;
    private String resourceMenuKey;
    private String resourceMenuIconType;
    private String resourceMenuPath;
    private String resourceMenuTitle;
    private int resourceSortNumber;

    public int getResourceSortNumber() {
        return resourceSortNumber;
    }

    public void setResourceSortNumber(int resourceSortNumber) {
        this.resourceSortNumber = resourceSortNumber;
    }

    public String getResourceMenuKey() {
        return resourceMenuKey;
    }

    public void setResourceMenuKey(String resourceMenuKey) {
        this.resourceMenuKey = resourceMenuKey;
    }

    public String getResourceMenuIconType() {
        return resourceMenuIconType;
    }

    public void setResourceMenuIconType(String resourceMenuIconType) {
        this.resourceMenuIconType = resourceMenuIconType;
    }

    public String getResourceMenuPath() {
        return resourceMenuPath;
    }

    public void setResourceMenuPath(String resourceMenuPath) {
        this.resourceMenuPath = resourceMenuPath;
    }

    public String getResourceMenuTitle() {
        return resourceMenuTitle;
    }

    public void setResourceMenuTitle(String resourceMenuTitle) {
        this.resourceMenuTitle = resourceMenuTitle;
    }

    public long getResourcePageId() {
        return resourcePageId;
    }

    public void setResourcePageId(long resourcePageId) {
        this.resourcePageId = resourcePageId;
    }

    public int getResourceAllAllow() {
        return resourceAllAllow;
    }

    public void setResourceAllAllow(int resourceAllAllow) {
        this.resourceAllAllow = resourceAllAllow;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    public long getMallId() {
        return mallId;
    }

    public void setMallId(long mallId) {
        this.mallId = mallId;
    }

    public int getResourceType() {
        return resourceType;
    }

    public void setResourceType(int resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public int getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(int resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

    public long getResourceParentId() {
        return resourceParentId;
    }

    public void setResourceParentId(long resourceParentId) {
        this.resourceParentId = resourceParentId;
    }

    public String getResourceAction() {
        return resourceAction;
    }

    public void setResourceAction(String resourceAction) {
        this.resourceAction = resourceAction;
    }

    public String getResourceCreateTime() {
        return resourceCreateTime;
    }

    public void setResourceCreateTime(String resourceCreateTime) {
        this.resourceCreateTime = resourceCreateTime;
    }

    public long getResourceCreateBy() {
        return resourceCreateBy;
    }

    public void setResourceCreateBy(long resourceCreateBy) {
        this.resourceCreateBy = resourceCreateBy;
    }
}
