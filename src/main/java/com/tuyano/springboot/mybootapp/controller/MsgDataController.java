//package com.tuyano.springboot.mybootapp.controller;
//
//import com.tuyano.springboot.mybootapp.dao.MsgDataDao;
//import com.tuyano.springboot.mybootapp.dao.MsgDataDaoImpl;
//import com.tuyano.springboot.mybootapp.dao.MyDataDaoImpl;
//import com.tuyano.springboot.mybootapp.entity.MsgData;
//import com.tuyano.springboot.mybootapp.repository.MsgDataRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.validation.Valid;
//import java.util.List;
//
//@Controller
//public class MsgDataController {
//
//    @Autowired
//    MsgDataRepository msgDataRepository;
//
//    @PersistenceContext
//    EntityManager entityManager;
//
//    MsgDataDaoImpl dao;
//
//    @RequestMapping(value = "/msg", method = RequestMethod.GET)
//    public ModelAndView msg(ModelAndView modelAndView){
//        modelAndView.setViewName("showMsgData");
//        modelAndView.addObject("title","sample");
//        modelAndView.addObject("msg","MsgData 예제입니다.");
//        MsgData msgData = new MsgData();
//        modelAndView.addObject("formModel", msgData);
//        List<MsgData> list = dao.getAll();
//        modelAndView.addObject("datalist", list);
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/msg", method = RequestMethod.POST)
//    public ModelAndView msgform(
//            @Valid @ModelAttribute MsgData msgData,
//            Errors result,
//            ModelAndView modelAndView){
//        if (result.hasErrors()){
//            modelAndView.setViewName("showMsgData");
//            modelAndView.addObject("title", "Sample [ERROR]");
//            modelAndView.addObject("msg","값을 다시 확인해주세요.");
//            return modelAndView;
//        }else {
//            msgDataRepository.save(msgData);
//            return new ModelAndView("redirect:/msg");
//        }
//    }
//
//    @PostConstruct
//    public void init(){
//        System.out.println("ok");
//        dao = new MsgDataDaoImpl(entityManager);
//    }
//
//}
