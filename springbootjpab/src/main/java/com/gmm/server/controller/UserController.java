package com.gmm.server.controller;

import com.gmm.server.model.User;
import com.gmm.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by john on 2017-10-01.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/springboot")
    public String Index(){
        return "redirect:list";
    }

    @RequestMapping("/list")
    public String getAll(ModelMap modelMap){
        Iterable<User> userIterable = userService.findAll();
        modelMap.addAttribute("userIterable",userIterable);
        return "list";
    }

    @RequestMapping("/info")
    public String findById(ModelMap modelMap, @RequestParam(value = "id", required = true) Integer id){
        User user = userService.findOne(id);
        modelMap.addAttribute("user", user);
        return "info";
    }

    @RequestMapping("/add")
    public String addUserPage(){
        return "add";
    }

    @RequestMapping("/add_submit")
    public String addUser(@RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "gender",required = false) String gender,
                          @RequestParam(value = "age",required = false) Integer age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setGender(gender);
        userService.addUser(user);
        return "redirect:list";
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam(value = "id",required = false) Integer id){
        userService.deleteUser(id);
        return "redirect:list";
    }

    @RequestMapping("/update")
    public String updateUserPage(ModelMap modelMap,@RequestParam(value = "id",required = true) Integer id){
        User user = userService.findOne(id);
        modelMap.addAttribute("user", user);
        return "update";
    }

    @RequestMapping("/update_submit")
    public String updateUser(@RequestParam(value = "id",required = false)Integer id,
                             @RequestParam(value = "name",required = false)String name,
                             @RequestParam(value = "gender",required = false)String gender,
                             @RequestParam(value = "age",required = false)Integer age){
      /*User user = new User();
        user.setId(id);
        user.setGender(gender);
        user.setAge(age);
        user.setName(name);*/
        userService.updateUser2(id,name,gender,age);
        return "redirect:list";
    }
}
