package com.hannea.model;

/**
 * Class
 *
 * @author wgm
 * @date 2018/03/16
 */
public class MITMallManageUser {
    private long operaterId;
    /**
     * 商城运营者id
     */
    public long getOperaterId() {
        return operaterId;
    }
    public void setOperaterId(long operaterId) {
        this.operaterId = operaterId;
    }

    private long mallId;
    /**
     * 关联商城id
     */
    public long getMallId() {
        return mallId;
    }
    public void setMallId(long mallId) {
        this.mallId = mallId;
    }

    private String operaterAccount;
    /**
     * 登录账号
     */
    public String getOperaterAccount() {
        return operaterAccount;
    }
    public void setOperaterAccount(String operaterAccount) {
        this.operaterAccount = operaterAccount;
    }

    private String operaterPassword;
    /**
     * 登录密码
     */
    public String getOperaterPassword() {
        return operaterPassword;
    }
    public void setOperaterPassword(String operaterPassword) {
        this.operaterPassword = operaterPassword;
    }

    private int operaterStatus;
    /**
     * 账号状态
     */
    public int getOperaterStatus() {
        return operaterStatus;
    }
    public void setOperaterStatus(int operaterStatus) {
        this.operaterStatus = operaterStatus;
    }

    private String operaterCreateTime;
    /**
     * 创建时间
     */
    public String getOperaterCreateTime() {
        return operaterCreateTime;
    }
    public void setOperaterCreateTime(String operaterCreateTime) {
        this.operaterCreateTime = operaterCreateTime;
    }

    private String operaterName;
    private long lastLoginTime;
    private int operaterAdministrator;

    public int getOperaterAdministrator() {
        return operaterAdministrator;
    }

    public void setOperaterAdministrator(int operaterAdministrator) {
        this.operaterAdministrator = operaterAdministrator;
    }

    public String getOperaterName() {
        return operaterName;
    }

    public void setOperaterName(String operaterName) {
        this.operaterName = operaterName;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
