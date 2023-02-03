package com.tuyano.springboot.mybootapp.controller;

import com.tuyano.springboot.mybootapp.entity.MyData;
import com.tuyano.springboot.mybootapp.service.MyDataService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Getter @Setter
@RequiredArgsConstructor
public class MyDataRestController {

    private final MyDataService myDataService;

    @RequestMapping("/")
    public List<MyData> restAll(){
        return myDataService.getAll();
    }

    @RequestMapping("/rest/{num}")
    public MyData restBy(@PathVariable int num){
        return myDataService.get(num);
    }


}
