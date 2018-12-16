package com.service.impl;
import com.dao.IRoleDao;
import com.service.IRoleService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@MapperScan(basePackages="com.dao")
@Service("roleService")
public class RoleService implements IRoleService{
    @Autowired
    private IRoleDao roleDao;
}
