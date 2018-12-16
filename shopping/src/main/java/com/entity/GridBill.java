package com.entity;

import java.util.List;

public class GridBill {
    private int total;
    private List<Bill> list;
    public GridBill() {
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public List<Bill> getList() {
        return list;
    }
    public void setList(List<Bill> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GridBill{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
