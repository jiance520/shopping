package com.entity;

import java.util.List;

public class GridUsers {
    private int total;
    private List<Users> list;

    public GridUsers() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Users> getList() {
        return list;
    }

    public void setList(List<Users> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GridUsers{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
