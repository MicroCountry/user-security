package com.hannea.model;

import java.io.Serializable;
import java.util.List;

/**
 * Class
 *
 * @author wgm
 * @date 2017/12/28
 */
public class MenuTreeNode implements Serializable{

    private static final long serialVersionUID = 6799091986680437622L;

    private long resourceId;
    private String resourceMenuKey;
    //是否为顶层菜单
    private String resourceMenuType;
    private String resourceMenuIconType;
    private String resourceMenuPath;
    private String resourceMenuTitle;
    private long resourceParentId;
    //排序位，越小越在前面
    private int resourceSortNumber;

    //子菜单
    private List<MenuTreeNode> nodeList;

    public long getResourceParentId() {
        return resourceParentId;
    }

    public void setResourceParentId(long resourceParentId) {
        this.resourceParentId = resourceParentId;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceMenuKey() {
        return resourceMenuKey;
    }

    public void setResourceMenuKey(String resourceMenuKey) {
        this.resourceMenuKey = resourceMenuKey;
    }

    public String getResourceMenuType() {
        return resourceMenuType;
    }

    public void setResourceMenuType(String resourceMenuType) {
        this.resourceMenuType = resourceMenuType;
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

    public int getResourceSortNumber() {
        return resourceSortNumber;
    }

    public void setResourceSortNumber(int resourceSortNumber) {
        this.resourceSortNumber = resourceSortNumber;
    }

    public List<MenuTreeNode> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<MenuTreeNode> nodeList) {
        this.nodeList = nodeList;
    }
}
