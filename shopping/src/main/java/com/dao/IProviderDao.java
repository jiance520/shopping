package com.dao;

import com.entity.Provider;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IProviderDao {
    List<Provider> selectAll();
    Provider selectByPrimaryKey(Long id);
    int deleteByPrimaryKey(Long id);
    int insert(Provider provider);
    int insertSelective(Provider provider);
    int updateByPrimaryKeySelective(Provider provider);
    int updateByPrimaryKey(Provider provider);
}
