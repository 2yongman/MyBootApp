package com.tuyano.springboot.mybootapp.dao;

import com.tuyano.springboot.mybootapp.entity.MsgData;

import java.util.List;

public interface MsgDataDao<T> {

    public List<MsgData> getAll();

    public MsgData findById(Long id);
}
