package com.service.impl;

import com.dao.IBillDao;
import com.entity.Bill;
import com.service.IBillService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Transactional
@MapperScan(basePackages="com.dao")
@Service("billService")
public class BillService implements IBillService{
    @Autowired
    private IBillDao billDao;

    @Override
    public List<Bill> selectAll() {
        return billDao.selectAll();
    }

    @Override
    public Bill selectByPrimaryKey(Long id) {
        return billDao.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return billDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<Map<String,Object>> selectAllMap(Map<String,Object> map) {
        return billDao.selectAllMap(map);
    }

    @Override
    public List<Map<String, Object>> selectSearchBill(Map<String, Object> map) {
        return billDao.selectSearchBill(map);
    }

    @Override
    public int deleteByBillCode(String billcode) {
        return billDao.deleteByBillCode(billcode);
    }

    @Override
    public int insert(Bill bill) {
        return billDao.insert(bill);
    }

    @Override
    public int insertSelective(Bill bill) {
        return billDao.insertSelective(bill);
    }

    @Override
    public int updateByPrimaryKeySelective(Bill bill) {
        return billDao.updateByPrimaryKeySelective(bill);
    }

    @Override
    public int updateByPrimaryKey(Bill bill) {
        return billDao.updateByPrimaryKey(bill);
    }
}
