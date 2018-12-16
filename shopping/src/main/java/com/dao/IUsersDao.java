package com.dao;

import com.entity.Role;
import com.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IUsersDao {
    List<Users> selectAll();
    Users selectLogin(Map map);
    Users selectByPrimaryKey(Long id);
    int insert(Users users);
    int insertSelective(Users users);
    int updateByPrimaryKeySelective(Users users);
    int updateByPrimaryKey(Users users);
}
