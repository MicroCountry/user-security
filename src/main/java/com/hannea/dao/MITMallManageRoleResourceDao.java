package com.hannea.dao;

import com.hannea.model.MITMallManageResource;
import com.hannea.model.MITMallManageRole;
import com.hannea.model.MITMallManageRoleResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Class
 *
 * @author wgm
 * @date 2017/12/27
 */
@Mapper
public interface MITMallManageRoleResourceDao {
    List<MITMallManageRoleResource> selectByRoleAndResource(@Param("mallId") long mallId, @Param("roleList") List<MITMallManageRole> roleList, @Param("resourceList") List<MITMallManageResource> resourceList);

    List<MITMallManageResource> selectResourceByMallAndUser(@Param("mallId") long mallId, @Param("userId") long userId);

    List<MITMallManageResource> selectResourceByMallAndRole(@Param("mallId") long mallId, @Param("roleId") long roleId);

    int batchInsert(@Param("list") List<MITMallManageRoleResource> roleResourceList);

    void deleteResource(@Param("roleId") long roleId);
}
