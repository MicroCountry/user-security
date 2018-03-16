package com.hannea.dao;

import com.hannea.model.MITMallManageRole;
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
public interface MITMallManageRoleDao {
    List<MITMallManageRole> selectByRoleByUserId(@Param("mallId") long mallId, @Param("userId") long userId);

    List<MITMallManageRole> selectByMallId(@Param("mallId") long mallId);

    int insert(MITMallManageRole mitMallManageRole);

    int updateRoleStatus(@Param("roleId") long roleId, @Param("status") int status);

    MITMallManageRole selectByRoleId(@Param("roleId") long roleId);

    MITMallManageRole selectAdmin(@Param("mallId") long mallId);

    List<MITMallManageRole> selectByRoleList(@Param("list") List<Long> list);
}
