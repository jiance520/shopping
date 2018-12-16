package com.service.impl;

import com.dao.IUsersDao;
import com.entity.Users;
import com.service.IUserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@MapperScan(basePackages="com.dao")
@Service("userService")
public class UserService  implements IUserService{
    @Autowired
    private IUsersDao usersDao;

    @Override
    public List<Users> selectAll() {
        return usersDao.selectAll();
    }

    @Override
    public Users selectLogin(Map map) {
        return usersDao.selectLogin(map);
    }
}
