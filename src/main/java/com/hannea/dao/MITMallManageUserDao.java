package com.hannea.dao;

import com.hannea.model.MITMallManageUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MITMallManageUserDao {
    List<MITMallManageUser> selectByAccountAndPwd(@Param("operaterAccount") String operaterAccount, @Param("operaterPassword") String operaterPassword);

    MITMallManageUser selectByPrimaryKey(Long operaterId);

    int insertSelective(MITMallManageUser MITMallManageUser);

    Long selectByAccount(@Param("account") String account);

    List<MITMallManageUser> selectByMallId(@Param("mallId") long mallId);

    int updateUser(@Param("userId") long userId, @Param("status") int status);

    int updateUserSelective(@Param("name") String name, @Param("password") String password, @Param("userId") long userId);

    int updateLoginTime(@Param("loginTime") long loginTime, @Param("operaterId") long operaterId);

    int updatePassword(@Param("operaterId") long operaterId, @Param("newPassword") String newPassword);
}
