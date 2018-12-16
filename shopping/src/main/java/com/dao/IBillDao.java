package com.dao;

import com.entity.Bill;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface IBillDao {
    List<Bill> selectAll();
    List<Map<String,Object>> selectAllMap(Map<String, Object> map);
    List<Map<String,Object>> selectSearchBill(Map<String, Object> map);
    Bill selectByPrimaryKey(Long id);
    int deleteByPrimaryKey(Long id);
    int deleteByBillCode(String billcode);
    int insert(Bill bill);
    int insertSelective(Bill bill);
    int updateByPrimaryKeySelective(Bill bill);
    int updateByPrimaryKey(Bill bill);
}
