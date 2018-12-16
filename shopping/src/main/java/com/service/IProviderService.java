package com.service;

import com.entity.Provider;

import java.util.List;

public interface IProviderService {
    int updateByPrimaryKeySelective(Provider provider);
    List<Provider> selectAll();
}
