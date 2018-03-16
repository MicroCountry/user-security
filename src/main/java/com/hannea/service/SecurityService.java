package com.hannea.service;


import com.hannea.model.MITMallManageResource;
import com.hannea.model.MITMallManageRole;
import com.hannea.model.MITMallManageUser;
import com.hannea.model.MenuTreeNode;

import java.util.List;

/**
 * Class
 *
 * @author wgm
 * @date 2017/12/27
 */
public interface SecurityService {

    int hasAuthority(long mallId, long userId, int resourceType, String resourceAction);

    List<MenuTreeNode> getUserMenuList(long mallId, long userId);

    List<MenuTreeNode> getMallAllMenu(long mallId);

    MITMallManageUser[] getMallAllUser(long mallId);

    List<MITMallManageRole> getMallAllRole(long mallId);

    MITMallManageRole[] getUserRole(long mallId, long userId);

    List<MITMallManageResource> getRoleResource(long mallId, long roleId);

    int addUser(long mallId, String account, String name, List<Long> list);

    int updateUserStatus(long userId);

    int addRole(long mallId, String roleName, String roleDescription, List<Long> list);

    int updateRoleStatus(long roleId);

    int updateRole(long mallId, long roleId, List<Long> list);

    int updateUser(long mallId, long userId, String account, String name, String password, List<Long> list);

    MITMallManageRole getRoleById(long roleId);

    MITMallManageRole getRoleAdmin(long mallId);

    List<MenuTreeNode> getRoleMenuList(long mallId, long roleId);

    MITMallManageRole[] getRoleList(List<Long> list);

    MITMallManageResource[] getResourceList(List<Long> list);

}
