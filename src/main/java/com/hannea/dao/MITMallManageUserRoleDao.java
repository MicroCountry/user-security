package com.hannea.dao;

import com.hannea.model.MITMallManageRole;
import com.hannea.model.MITMallManageUserRole;
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
public interface MITMallManageUserRoleDao {
    List<MITMallManageRole> selectByMallAndUserId(@Param("mallId") long mallId, @Param("userId") long userId);

    int batchInsert(@Param("list") List<MITMallManageUserRole> userRoleList);

    void deleteUserRole(@Param("userId") long userId);
}
