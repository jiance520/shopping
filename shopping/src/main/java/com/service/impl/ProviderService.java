package com.service.impl;

import com.dao.IProviderDao;
import com.entity.Provider;
import com.service.IProviderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@MapperScan(basePackages="com.dao")
@Service("providerService")
public class ProviderService implements IProviderService{
    @Autowired
    private IProviderDao providerDao;

    @Override
    public int updateByPrimaryKeySelective(Provider provider) {
        return providerDao.updateByPrimaryKeySelective(provider);
    }

    @Override
    public List<Provider> selectAll() {
        return providerDao.selectAll();
    }
}
