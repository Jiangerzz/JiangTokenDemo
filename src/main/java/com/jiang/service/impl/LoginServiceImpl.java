package com.jiang.service.impl;

import com.jiang.domain.LoginUser;
import com.jiang.domain.Result;
import com.jiang.domain.User;
import com.jiang.service.LoginService;
import com.jiang.utils.JwtUtil;
import com.jiang.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public Result login(User user) {
        //用户认证
        //封装用户信息
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        //进行认证，返回对象封装用户信息
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //认证失败，返回错误提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登陆失败");
        }
        //认证通过，生成jwt，返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>();
        map.put("token",jwt);
        //把完整的用户信息存入到redis中 userId作为key
        redisCache.setCacheObject("login:" + userId,loginUser);
        return new Result(200,"登陆成功",map);
    }

    @Override
    public Result logout() {
        //获取SecurityContextHolder中的用户id
        Authentication authentication =(UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login:" + userId);
        return new Result(200,"注销成功");
    }
}
