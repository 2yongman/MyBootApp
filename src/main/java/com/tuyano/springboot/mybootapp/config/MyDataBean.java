//package com.tuyano.springboot.mybootapp.config;
//
//import com.tuyano.springboot.mybootapp.entity.MyData;
//import com.tuyano.springboot.mybootapp.repository.MyDataRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Optional;
//
//public class MyDataBean {
//
//    @Autowired
//    MyDataRepository myDataRepository;
//
//    public String getTableTagById(Long id){
//        Optional<MyData> data = myDataRepository.findById(id);
//        String result = "<tr><td>" + data.getName;
//        }
//    }
//}
