package com.tuyano.springboot.mybootapp.controller;

import com.tuyano.springboot.mybootapp.dao.MyDataDaoImpl;
import com.tuyano.springboot.mybootapp.entity.MyData;
import com.tuyano.springboot.mybootapp.repository.MyDataRepository;
import com.tuyano.springboot.mybootapp.service.MyDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HelloController {

    EntityManager entityManager;

    private final MyDataService myDataService;

    private final MyDataRepository myDataRepository;

    @RequestMapping(value = "/page/{num}", method = RequestMethod.GET)
    public ModelAndView page(@PathVariable int num, ModelAndView modelAndView){
        modelAndView.setViewName("index");
        Page<MyData> page =myDataService.getMYDataInPage(num);
        modelAndView.addObject("title", "Find Page");
        modelAndView.addObject("msg","MYDATA의 예제입니다.");
        modelAndView.addObject("pagenumber", num);
        modelAndView.addObject("datalist", page);
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("index");
        modelAndView.addObject("title","Find Page");
        modelAndView.addObject("msg","MyData의 예제입니다.");
        List<MyData> list = myDataService.getAll();
        return modelAndView;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView find(ModelAndView modelAndView){
        modelAndView.setViewName("find");
        modelAndView.addObject("title", "Find Page");
        modelAndView.addObject("msg", "MyData의 예제입니다.");
        modelAndView.addObject("value", "");
        List<MyData> list = myDataService.getAll();
        modelAndView.addObject("datalist", list);
        return modelAndView;
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest httpServletRequest, ModelAndView modelAndView){
        modelAndView.setViewName("find");
        String param = httpServletRequest.getParameter("fstr");
        if (param == ""){
            modelAndView = new ModelAndView("redirect:/find");
        }else {
            modelAndView.addObject("title", "Find result");
            modelAndView.addObject("msg" , "r" + param + "ㄴ의 검색결과");
            modelAndView.addObject("value",param);
            List<MyData> list = myDataService.find(param);
            modelAndView.addObject("datalist",list);
        }
        return modelAndView;
    }



    @PostConstruct
    public void init(){
        MyData d1 = new MyData();
        d1.setName("tuyano");
        d1.setAge(123);
        d1.setEmail("Kim@gilbut.co.kr");
        d1.setMemo("090999999");
        myDataRepository.save(d1);

        MyData d2 = new MyData();
        d2.setName("lee");
        d2.setAge(15);
        d2.setEmail("lee@flower");
        d2.setMemo("080888888");
        myDataRepository.save(d2);

        MyData d3 = new MyData();
        d3.setName("choi");
        d3.setAge(37);
        d3.setEmail("choi@happy");
        d3.setMemo("07077777");
        myDataRepository.save(d3);
    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ModelAndView index(@ModelAttribute("formModel") MyData myData, ModelAndView modelAndView){
//        modelAndView.setViewName("index");
//        modelAndView.addObject("msg", "this is sample content.");
//        modelAndView.addObject("formModel", myData);
//        List<MyData> myData1 = myDataRepository.findAll();
//        modelAndView.addObject("datalist",myData1);
//        return modelAndView;
//    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView form(@ModelAttribute("formModel") @Validated MyData myData, BindingResult result, ModelAndView modelAndView){
        ModelAndView res = null;
        if (!result.hasErrors()){
            myDataRepository.saveAndFlush(myData);
            res = new ModelAndView("redirect:/");
        } else {
            modelAndView.setViewName("index");
            modelAndView.addObject("msg","sorry, error is occured....");
            Iterable<MyData> list = myDataRepository.findAll();
            modelAndView.addObject("datalist",list);
            res = modelAndView;
        }
        return res;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute MyData myData, @PathVariable int id, ModelAndView modelAndView){
        modelAndView.setViewName("edit");
        modelAndView.addObject("title", "edit mydata.");
        Optional<MyData> data = myDataRepository.findById((long)id);
        modelAndView.addObject("formModel",data);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView update(@ModelAttribute MyData myData, ModelAndView modelAndView){
        myDataRepository.save(myData);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id, ModelAndView modelAndView){
        modelAndView.setViewName("delete");
        modelAndView.addObject("title","delete mydata.");
        Optional<MyData> data = myDataRepository.findById(id);
        modelAndView.addObject("formModel",data);
        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView remove(@RequestParam Long id, ModelAndView mav){
        mav.setViewName("delete");
        myDataRepository.deleteById(id);
        return new ModelAndView("redirect:/");
    }




}