package com.hannea.dao;

import com.hannea.model.MITMallManageResource;
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
public interface MITMallManageResourceDao {
    List<MITMallManageResource> selectByResourceTypeAndAction(@Param("mallId") long mallId, @Param("resourceType") int resourceType, @Param("resourceAction") String resourceAction);

    List<MITMallManageResource> selectAllMallMenu(@Param("mallId") long mallId);

    List<MITMallManageResource> selectApiByResourceIdList(@Param("list") List<Long> list);

    List<MITMallManageResource> selectPageByResourceIdList(List<Long> pageIdList);
}
