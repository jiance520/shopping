package com.entity;

import java.util.List;

public class GridProvider {
    private int total;
    private List<Provider> list;

    public GridProvider() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Provider> getList() {
        return list;
    }

    public void setList(List<Provider> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GridProvider{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
