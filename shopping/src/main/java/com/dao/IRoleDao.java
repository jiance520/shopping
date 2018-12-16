package com.dao;

import com.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IRoleDao {
    Role selectByPrimaryKey(Long id);
    int deleteByPrimaryKey(Long id);
    int insert(Role role);
    int insertSelective(Role role);
    int updateByPrimaryKeySelective(Role role);
    int updateByPrimaryKey(Role role);
}
