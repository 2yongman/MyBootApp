//package com.tuyano.springboot.mybootapp.dao;
//
//import com.tuyano.springboot.mybootapp.entity.MsgData;
//import com.tuyano.springboot.mybootapp.entity.MyData;
//
//import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder;
//import java.util.List;
//
//public class MsgDataDaoImpl implements MsgDataDao<MsgData>{
//
//    private EntityManager entityManager;
//
//    public MsgDataDaoImpl(){
//    }
//
//    public MsgDataDaoImpl(EntityManager manager){
//        entityManager = manager;
//    }
//
//    @Override
//    public List<MsgData> getAll(){
//    }
//
//    @Override
//    public MsgData findById(Long id){
//        return (MsgData)entityManager.createQuery("from MsgData where id =" + id)
//                .getSingleResult();
//    }
//}
