package com.service;

import com.entity.Users;

import java.util.List;
import java.util.Map;

public interface IUserService {
    List<Users> selectAll();
    Users selectLogin(Map map);
}
