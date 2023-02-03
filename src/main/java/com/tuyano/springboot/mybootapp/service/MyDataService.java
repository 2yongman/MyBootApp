package com.tuyano.springboot.mybootapp.service;

import com.tuyano.springboot.mybootapp.entity.MyData;
import com.tuyano.springboot.mybootapp.repository.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class MyDataService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    MyDataRepository myDataRepository;

    private static final int PAGE_SIZE = 3; // 한 페이지당 엔티티 수

    public List<MyData> getAll(){
        return (List<MyData>)entityManager.createQuery("from MyData").getResultList();
    }

    public MyData get(int num){
        return (MyData)entityManager.createQuery("select d from MyData d where d.id= " + num)
                .getSingleResult();
    }

    public List<MyData> find(String fstr){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
        Root<MyData> root = query.from(MyData.class);
        query.select(root).where(builder.equal(root.get("name"), fstr));
        List<MyData> list = null;
        list = (List<MyData>)entityManager.createQuery(query).getResultList();
        return list;
    }

    public Page<MyData> getMYDataInPage(Integer pageNumber){
        PageRequest pageRequest = PageRequest.of(pageNumber -1, PAGE_SIZE);
        return myDataRepository.findAll(pageRequest);
    }
}
