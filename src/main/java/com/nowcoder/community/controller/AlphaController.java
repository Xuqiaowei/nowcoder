package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return  "Hello Spring Boot!";
    }
    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求行
        System.out.println(request.getMethod());
        System.out.println((request.getServletPath()));
        //获取请求头
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name=enumeration.nextElement();
            String value=request.getHeader(name);
            System.out.println(name+":"+value);
        }
        //获取请求体参数
        System.out.println(request.getParameter("code"));

        //返回相应数据
        response.setContentType("text/html;charset=utf-8");
        try(PrintWriter writer = response.getWriter();
        ){
            writer.write("<hl>牛客网</hl>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //GET请求的处理
    //限定路径和处理请求的方式
    @RequestMapping(path="/students",method= RequestMethod.GET)
    @ResponseBody
    public String getstudents(
            @RequestParam (name="current",required=false,defaultValue ="1")int current,
            @RequestParam (name="limit",required=false,defaultValue ="10")int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }
    @RequestMapping(path="/student/{id}",method= RequestMethod.GET)
    @ResponseBody
    public String getstudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    //POST请求的处理
    @RequestMapping(path="/student",method=RequestMethod.POST)
    @ResponseBody
    public String savestudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }
    //响应html 方式1
    @RequestMapping(path="/teacher",method=RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age","30");
        mav.setViewName("/demo/view");
        return mav;
    }
    //响应html 方式2
    @RequestMapping(path="/school",method=RequestMethod.GET)
    public String getschool(Model model){
        model.addAttribute("name","同济大学");
        model.addAttribute("age","115");
           return "/demo/view";
    }
    //响应JSON数据（通常在异步请求中）
    //java对象-json字符串-js对象
    @RequestMapping(path="/emp",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEMP(){
        Map<String,Object> emp=new HashMap<>();
        emp.put("name","张三");
        emp.put("age","23");
        emp.put("salary","8000");
        return emp;
    }

}
