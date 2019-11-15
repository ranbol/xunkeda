package com.example.entity.dao;

import com.example.entity.po.AppLogin;

public interface AppLoginMapper {
    int deleteByPrimaryKey(Integer login_id);

    int insert(AppLogin record);

    int insertSelective(AppLogin record);

    AppLogin selectByPrimaryKey(Integer login_id);

    int updateByPrimaryKeySelective(AppLogin record);

    int updateByPrimaryKey(AppLogin record);
}