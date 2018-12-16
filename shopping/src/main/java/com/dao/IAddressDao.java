package com.dao;

import com.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IAddressDao {
    Address selectByPrimaryKey(Long id);
    int deleteByPrimaryKey(Long id);
    int insert(Address address);
    int insertSelective(Address address);
    int updateByPrimaryKeySelective(Address address);
    int updateByPrimaryKey(Address address);
}
