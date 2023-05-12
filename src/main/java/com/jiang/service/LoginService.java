package com.jiang.service;

import com.jiang.domain.Result;
import com.jiang.domain.User;

public interface LoginService {
    
    Result login(User user);

    Result logout();
}
