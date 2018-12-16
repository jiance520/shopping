package com.service.impl;

import com.dao.IAddressDao;
import com.service.IAddressService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@MapperScan(basePackages="com.dao")
@Service("addressService")
public class AddressService implements IAddressService{
    @Autowired
    private IAddressDao addressDao;
}
