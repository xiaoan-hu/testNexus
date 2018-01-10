package com.bdqn.t0724.bootcachedemo.model;

import java.io.Serializable;
import java.util.List;

// 这个对象对应数据库的book表（中的一行数据）
public class Book implements Serializable {
    private Integer id;
    private String name;
    private String author;
    private Integer year;
    private Integer maxDays;
    private Boolean lent;
    private Boolean available;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMaxDays() {
        return maxDays;
    }

    public void setMaxDays(Integer maxDays) {
        this.maxDays = maxDays;
    }

    public Boolean getLent() {
        return lent;
    }

    public void setLent(Boolean lent) {
        this.lent = lent;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
