package com.jiang.controller;


import com.jiang.domain.Result;
import com.jiang.domain.User;
import com.jiang.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    
    @Autowired
    private LoginService loginService;
    
    @PostMapping("/user/login")
    public Result login(@RequestBody User user){
        //登录
       return loginService.login(user);
    }
    
    @GetMapping("/user/logout")
    public Result logout(){
        return loginService.logout();
    }
}
