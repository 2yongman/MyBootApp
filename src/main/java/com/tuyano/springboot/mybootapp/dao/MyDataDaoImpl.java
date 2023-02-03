package com.tuyano.springboot.mybootapp.dao;

import com.tuyano.springboot.mybootapp.entity.MyData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MyDataDaoImpl implements MyDataDao<MyData>{

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    public MyDataDaoImpl(){
        super();
    }

    public MyDataDaoImpl(EntityManager manager){
        entityManager = manager;
    }


    @Override
    public List<MyData> getAll(){
        Query query = entityManager.createQuery("from MyData");
        List<MyData> list = query.getResultList();
        return list;
    }

    @Override
    public MyData findById(long id){
        return (MyData)entityManager.createQuery("from MyData where id =" + id).getSingleResult();
    }

    @Override
    public List<MyData> findByName(String name){
        return (List<MyData>)entityManager.createQuery("from MyData where name=" + name).getResultList();
    }

//    @Override
//    public List<MyData> find(String fstr){
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
//        Root<MyData> root = query.from(MyData.class);
//        query.select(root).where(builder.equal(root.get("name"),fstr));
//        List<MyData> list = null;
//        list = (List<MyData>)entityManager.createQuery(query).getResultList();
//        return list;
//    }

//    @Override
//    public List<MyData> find(String fstr){
//        List<MyData> list = null;
//        String qstr = "from MyData where id = :fid or name like : fname or email like :femail";
//        Long fid = 0L;
//        try {
//            fid = Long.parseLong(fstr);
//        }catch (NumberFormatException e){
//            //e.printStackTrace();
//        }
//        Query query = entityManager.createQuery(qstr).setParameter("fid",fid)
//                .setParameter("fname","%"+fstr+"%")
//                .setParameter("femail", fstr+"@%");
//        list = query.getResultList();
//        return list;
//    }

    @Override
    public List<MyData> find(String fstr){
        List<MyData> list = null;
        Long fid = 0L;
        try {
            fid = Long.parseLong(fstr);
        }catch (NumberFormatException e){

        }
        Query query = entityManager.createNamedQuery("findWithName")
                .setParameter("fname", "%"+fstr+"%");
        list = query.getResultList();
        return list;
    }

    @Override
    public List<MyData> findByAge(int min, int max){
        return (List<MyData>)entityManager
                .createNamedQuery("findByAge")
                .setParameter("min",min)
                .setParameter("max",max)
                .getResultList();
    }

}
