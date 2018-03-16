package com.hannea.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.hannea.dao.*;
import com.hannea.model.*;
import com.hannea.service.SecurityService;
import com.hannea.util.IdWorkerFactory;
import com.hannea.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

/**
 * Class
 *
 * @author wgm
 * @date 2017/12/27
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    private MITMallManageResourceDao mitMallManageResourceDao;

    @Autowired
    private MITMallManageRoleDao mitMallManageRoleDao;

    @Autowired
    private MITMallManageRoleResourceDao mitMallManageRoleResourceDao;

    @Autowired
    private MITMallManageUserRoleDao mitMallManageUserRoleDao;

    @Autowired
    private MITMallManageUserDao mitMallManageUserDao;


    @Override
    public int hasAuthority(long mallId, long userId, int resourceType, String resourceAction) {
        MITMallManageUser MITMallManageUser = mitMallManageUserDao.selectByPrimaryKey(userId);
        if(MITMallManageUser == null){
            return 0;
        }
        if(MITMallManageUser.getOperaterAdministrator() == 1){
            //超级管理员
            return 1;
        }
        //获取资源信息
        List<MITMallManageResource> resourceList = mitMallManageResourceDao.selectByResourceTypeAndAction(0, resourceType, resourceAction);
        if(resourceList == null || resourceList.isEmpty()){
            return 0;
        }

        //获取所属页面
        List<Long> pageIdList = new ArrayList<>();
        for(MITMallManageResource resource : resourceList){
            //如果标志为所有人都有权限，直接返回允许
            if(resource.getResourceAllAllow() == 1){
                return 1;
            }
            if(resource.getResourcePageId() > 0) {
                pageIdList.add(resource.getResourcePageId());
            }
        }

        List<MITMallManageResource> pageList = mitMallManageResourceDao.selectPageByResourceIdList(pageIdList);
        if(pageList != null && !pageList.isEmpty()){
            for(MITMallManageResource resource : pageList){
                resourceList.add(resource);
            }
        }

        //获取角色信息
        List<MITMallManageRole> roleList = mitMallManageRoleDao.selectByRoleByUserId(mallId, userId);
        if(roleList == null || roleList.isEmpty()){
            return 0;
        }
        //获取角色资源关系
        List<MITMallManageRoleResource> rrList = mitMallManageRoleResourceDao.selectByRoleAndResource(mallId, roleList, resourceList);
        if(rrList == null || rrList.isEmpty()){
            return 0;
        }

        return 1;
    }

    @Override
    public List<MenuTreeNode> getUserMenuList(long mallId, long userId) {
        MITMallManageUser MITMallManageUser = mitMallManageUserDao.selectByPrimaryKey(userId);
        if(MITMallManageUser.getOperaterAdministrator() == 1){
            //超级管理员
            List<MITMallManageResource> list = mitMallManageResourceDao.selectAllMallMenu(0);
            return getTreeNode(list);
        }
        List<MITMallManageResource> menuList = mitMallManageRoleResourceDao.selectResourceByMallAndUser(mallId, userId);
        if(menuList == null || menuList.isEmpty()){
            return new ArrayList<>();
        }
        return getTreeNode(menuList);
    }

    private  List<MenuTreeNode> getTreeNode(List<MITMallManageResource> menuList){
        Map<String, List<MITMallManageResource>> map = new HashMap<>();
        for(MITMallManageResource resource : menuList){
            //顶级菜单
            if(resource.getResourceParentId() == 0){
                List<MITMallManageResource> subList = map.get("TOP");
                if(subList == null){
                    subList = new ArrayList<>();
                    subList.add(resource);
                    map.put("TOP", subList);
                }else {
                    subList.add(resource);
                    map.put("TOP", subList);
                }
            }else {
                if(map.containsKey(String.valueOf(resource.getResourceParentId()))){
                    List<MITMallManageResource> subList = map.get(String.valueOf(resource.getResourceParentId()));
                    subList.add(resource);
                    map.put(String.valueOf(resource.getResourceParentId()), subList);
                }else {
                    List<MITMallManageResource> subList = new ArrayList<>();
                    subList.add(resource);
                    map.put(String.valueOf(resource.getResourceParentId()), subList);
                }
            }
        }
        List<MITMallManageResource> topMenuList = map.get("TOP");
        List<MenuTreeNode> menuTreeNodes = new ArrayList<>();
        if(topMenuList != null || !topMenuList.isEmpty()){
            for(MITMallManageResource resource : topMenuList){
                menuTreeNodes.add(createTreeNode(resource, map));
            }
        }
        Collections.sort(menuTreeNodes, new MenuComparator());
        return menuTreeNodes;
    }

    private MenuTreeNode createTreeNode(MITMallManageResource resource, Map<String, List<MITMallManageResource>> map) {
        MenuTreeNode treeNode = new MenuTreeNode();
        treeNode.setResourceId(resource.getResourceId());
        treeNode.setResourceMenuIconType(resource.getResourceMenuIconType());
        treeNode.setResourceMenuKey(resource.getResourceMenuKey());
        treeNode.setResourceMenuPath(resource.getResourceMenuPath());
        treeNode.setResourceMenuTitle(resource.getResourceMenuTitle());
        treeNode.setResourceParentId(resource.getResourceParentId());
        treeNode.setResourceSortNumber(resource.getResourceSortNumber());
        if(resource.getResourceParentId() == 0){
            treeNode.setResourceMenuType("TOP");
        }else {
            treeNode.setResourceMenuType("SUB");
        }
        //从map中找到该菜单的子级菜单，把子级菜单加入到当前菜单TreeNodeList中
        List<MITMallManageResource> subMenuArr = map.get(String.valueOf(resource.getResourceId()));
        if (subMenuArr != null && subMenuArr.size() > 0) {
            List<MenuTreeNode> subTreeNode = new ArrayList<>();
            for (MITMallManageResource subMenu : subMenuArr) {
                //利用递归方案把所有子级菜单都加入对应的菜单项中的tree中
                subTreeNode.add(createTreeNode(subMenu, map));
            }
            if (subTreeNode.size() > 0) {
                Collections.sort(subTreeNode, new MenuComparator());
                treeNode.setNodeList(subTreeNode);
            }
        }
        return treeNode;
    }

    class MenuComparator implements Comparator<MenuTreeNode>{

        @Override
        public int compare(MenuTreeNode o1, MenuTreeNode o2) {
            return Integer.valueOf(o1.getResourceSortNumber()).compareTo(Integer.valueOf(o2.getResourceSortNumber()));
        }
    }

    @Override
    public List<MenuTreeNode> getMallAllMenu(long mallId) {
        List<MITMallManageResource> list = mitMallManageResourceDao.selectAllMallMenu(0);
        //筛选掉 管理员管理、岗位管理
        List<MITMallManageResource> menuList = new ArrayList<>();
        for(MITMallManageResource resource : list){
            if(!("管理员管理".equalsIgnoreCase(resource.getResourceName().trim())
                    || "岗位管理".equalsIgnoreCase(resource.getResourceName().trim())
                    || "操作日志".equalsIgnoreCase(resource.getResourceName().trim()))){
                menuList.add(resource);
            }
        }
        if(menuList == null || menuList.isEmpty()){
            return new ArrayList<>();
        }
        return getTreeNode(menuList);
    }

    @Override
    public MITMallManageUser[] getMallAllUser(long mallId) {
        List<MITMallManageUser> list = mitMallManageUserDao.selectByMallId(mallId);
        return list == null ? new MITMallManageUser[0] : list.toArray(new MITMallManageUser[list.size()]);
    }

    @Override
    public List<MITMallManageRole> getMallAllRole(long mallId) {
        return mitMallManageRoleDao.selectByMallId(mallId);
    }

    @Override
    public MITMallManageRole[] getUserRole(long mallId, long userId) {
        List<MITMallManageRole> list = mitMallManageUserRoleDao.selectByMallAndUserId(mallId, userId);
        return list == null ? new MITMallManageRole[0] : list.toArray(new MITMallManageRole[list.size()]);
    }

    @Override
    public List<MITMallManageResource> getRoleResource(long mallId, long roleId) {
        return mitMallManageRoleResourceDao.selectResourceByMallAndRole(mallId, roleId);
    }

    @Transactional
    @Override
    public int addUser(long mallId, String account, String name, List<Long> list) {
        try {
            MITMallManageUser MITMallManageUser = new MITMallManageUser();
            MITMallManageUser.setMallId(mallId);
            MITMallManageUser.setOperaterCreateTime(String.valueOf(System.currentTimeMillis()));
            MITMallManageUser.setOperaterStatus(1);
            MITMallManageUser.setOperaterName(name);
            MITMallManageUser.setOperaterAccount(account);
            String operaterPwd = Md5Util.md5("123456");	// 默认密码
            MITMallManageUser.setOperaterPassword(operaterPwd);
            MITMallManageUser.setOperaterId(IdWorkerFactory.getIdWorker().nextId());
            int cnt = mitMallManageUserDao.insertSelective(MITMallManageUser);
            if(cnt <= 0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();;
                return 0;
            }
            // 添加角色与用户关系

            int arrSize = list.size();
            List<MITMallManageUserRole> userRoleList = new ArrayList<>();
            for(int i=0; i< arrSize; i++) {
                MITMallManageUserRole mitMallManageUserRole = new MITMallManageUserRole();
                mitMallManageUserRole.setMallId(mallId);
                mitMallManageUserRole.setUserId(MITMallManageUser.getOperaterId());
                mitMallManageUserRole.setUserRoleCreateTime(System.currentTimeMillis());
                mitMallManageUserRole.setUserRoleStatus(1);
                mitMallManageUserRole.setRoleId(list.get(i));
                mitMallManageUserRole.setUserRoleId(IdWorkerFactory.getIdWorker().nextId());
                userRoleList.add(mitMallManageUserRole);
            }
            int upt = mitMallManageUserRoleDao.batchInsert(userRoleList);
            if(upt <= 0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();;
                return 0;
            }
        }catch (Exception e){
            logger.error("[addUser] error :"+ e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();;
            return 0;
        }

        return 1;
    }

    @Override
    public int updateUserStatus(long userId) {
        int status = 1;
        MITMallManageUser MITMallManageUser = mitMallManageUserDao.selectByPrimaryKey(userId);
        if(MITMallManageUser.getOperaterAdministrator() == 1){
            return 0;
        }
        if(MITMallManageUser.getOperaterStatus() == 1){
            status = -1;
        }
        return mitMallManageUserDao.updateUser(userId,status);
    }

    @Transactional
    @Override
    public int updateUser(long mallId, long userId, String account, String name, String password, List<Long> list) {
        try {
            if(!StringUtils.isEmpty(name) || !StringUtils.isEmpty(password)) {
                mitMallManageUserDao.updateUserSelective(name, StringUtils.isEmpty(password) ? null : password, userId);
            }
            mitMallManageUserRoleDao.deleteUserRole(userId);
            List<MITMallManageUserRole> userRoleList = new ArrayList<>();

            int arrSize = list.size();
            for(int i=0; i< arrSize; i++) {
                MITMallManageUserRole mitMallManageUserRole = new MITMallManageUserRole();
                mitMallManageUserRole.setMallId(mallId);
                mitMallManageUserRole.setUserId(userId);
                mitMallManageUserRole.setUserRoleCreateTime(System.currentTimeMillis());
                mitMallManageUserRole.setUserRoleStatus(1);
                mitMallManageUserRole.setRoleId(list.get(i));
                mitMallManageUserRole.setUserRoleId(IdWorkerFactory.getIdWorker().nextId());
                userRoleList.add(mitMallManageUserRole);
            }
            int upt = mitMallManageUserRoleDao.batchInsert(userRoleList);
            if(upt <= 0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();;
                return 0;
            }
        }catch (Exception e){
            logger.error("[updateUser] error :"+ e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();;
            return 0;
        }
        return 1;
    }

    @Transactional
    @Override
    public int addRole(long mallId, String roleName, String roleDescription, List<Long> list) {
        try {

            logger.info("list:"+ JSONObject.toJSONString(list));
            if("超级管理员".equalsIgnoreCase(roleName)){
                return 0;
            }
            MITMallManageRole mitMallManageRole = new MITMallManageRole();
            mitMallManageRole.setMallId(mallId);
            mitMallManageRole.setRoleName(roleName);
            mitMallManageRole.setRoleDescription(roleDescription);
            mitMallManageRole.setRoleStatus(1);
            mitMallManageRole.setRoleId(IdWorkerFactory.getIdWorker().nextId());
            int cnt = mitMallManageRoleDao.insert(mitMallManageRole);
            if(cnt <= 0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return 0;
            }

            List<MITMallManageRoleResource> roleResourceList = new ArrayList<>();
            int arrSize = list.size();

            for(int i = 0; i< list.size() ;i ++) {
                MITMallManageRoleResource roleResource = new MITMallManageRoleResource();
                roleResource.setMallId(mallId);
                roleResource.setResourceId(list.get(i));
                roleResource.setRoleId(mitMallManageRole.getRoleId());
                roleResource.setRoleResourceStatus(1);
                roleResource.setRoleResourceId(IdWorkerFactory.getIdWorker().nextId());
                roleResourceList.add(roleResource);
            }
            logger.info("roleResource:"+ arrSize);
            int upt = mitMallManageRoleResourceDao.batchInsert(roleResourceList);
            if(upt <= 0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return 0;
            }
            //增加页面接口权限
            /*List<MITMallManageResource> pageApi = mitMallManageResourceDao.selectApiByResourceIdList(list);
            logger.info("API:"+JSONObject.toJSONString(pageApi));
            List<MITMallManageRoleResource> apiRoleResourceList = new ArrayList<>();
            int arrSize2 = pageApi.size();
            Long[] idArr2 = identityService.genSomeRandomId(arrSize2);
            for(int i= 0 ; i < arrSize2 ; i ++) {
                MITMallManageRoleResource roleResource = new MITMallManageRoleResource();
                roleResource.setMallId(mallId);
                roleResource.setResourceId(pageApi.get(i).getResourceId());
                roleResource.setRoleId(mitMallManageRole.getRoleId());
                roleResource.setRoleResourceStatus(1);
                roleResource.setRoleResourceId(idArr2[i]);
                apiRoleResourceList.add(roleResource);
            }
            int count = mitMallManageRoleResourceDao.batchInsert(apiRoleResourceList);
            if(count <= 0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return 0;
            }*/
        }catch (Exception e){
            logger.error("[addRole] error :"+  e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public int updateRoleStatus(long roleId) {
        int status = 1;
        MITMallManageRole mitMallManageRole = mitMallManageRoleDao.selectByRoleId(roleId);
        if(mitMallManageRole.getRoleStatus() == 1){
            status = -1;
        }
        return mitMallManageRoleDao.updateRoleStatus(roleId,status);
    }

    @Transactional
    @Override
    public int updateRole(long mallId, long roleId, List<Long> list) {
        try {
            mitMallManageRoleResourceDao.deleteResource(roleId);
            List<MITMallManageRoleResource> roleResourceList = new ArrayList<>();
            int arrSize = list.size();

            for(int i =0 ; i< arrSize ; i++) {
                MITMallManageRoleResource roleResource = new MITMallManageRoleResource();
                roleResource.setMallId(mallId);
                roleResource.setResourceId(list.get(i));
                roleResource.setRoleId(roleId);
                roleResource.setRoleResourceStatus(1);
                roleResource.setRoleResourceId(IdWorkerFactory.getIdWorker().nextId());
                roleResourceList.add(roleResource);
            }
            int upt = mitMallManageRoleResourceDao.batchInsert(roleResourceList);
            if(upt <= 0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();;
                return 0;
            }
           /* List<MITMallManageResource> pageApi = mitMallManageResourceDao.selectApiByResourceIdList(list);
            List<MITMallManageRoleResource> apiRoleResourceList = new ArrayList<>();

            int apiSize = pageApi.size();
            Long[] idApi = identityService.genSomeRandomId(apiSize);
            for(int i =0; i< apiSize; i++) {
                MITMallManageRoleResource roleResource = new MITMallManageRoleResource();
                roleResource.setMallId(mallId);
                roleResource.setResourceId(pageApi.get(i).getResourceId());
                roleResource.setRoleId(roleId);
                roleResource.setRoleResourceStatus(1);
                roleResource.setRoleResourceId(idApi[i]);
                apiRoleResourceList.add(roleResource);
            }
            int count = mitMallManageRoleResourceDao.batchInsert(apiRoleResourceList);
            if(count <= 0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return 0;
            }*/
        }catch (Exception e){
            logger.error("[addRole] error :"+ e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }

        return 1;
    }

    @Override
    public MITMallManageRole getRoleById(long roleId) {
        return mitMallManageRoleDao.selectByRoleId(roleId);
    }

    @Override
    public MITMallManageRole getRoleAdmin(long mallId) {
        return mitMallManageRoleDao.selectAdmin(mallId);
    }

    @Override
    public List<MenuTreeNode> getRoleMenuList(long mallId, long roleId) {
        List<MITMallManageResource> list = getRoleResource(mallId, roleId);
        if(list == null || list.isEmpty()){
            return new ArrayList<>();
        }
        return getTreeNode(list);
    }

    @Override
    public MITMallManageRole[] getRoleList(List<Long> list) {
        if(list == null || list.isEmpty()){
            list = new ArrayList<>();
            list.add(0L);
        }
        List<MITMallManageRole> list1 = mitMallManageRoleDao.selectByRoleList(list);
        return list1 == null ? new MITMallManageRole[0] : list1.toArray(new MITMallManageRole[list1.size()]);
    }

    @Override
    public MITMallManageResource[] getResourceList(List<Long> list) {
        List<MITMallManageResource> list1 = mitMallManageResourceDao.selectPageByResourceIdList(list);
        return list1 == null ? new MITMallManageResource[0] : list1.toArray(new MITMallManageResource[list1.size()]);
    }
    
}
