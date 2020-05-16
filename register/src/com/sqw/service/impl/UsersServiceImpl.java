package com.sqw.service.impl;

import com.sqw.mapper.UsersMapper;
import com.sqw.pojo.Users;
import com.sqw.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UsersServiceImpl  implements UsersService {
    @Resource
    private UsersMapper usersMapper;
    @Override
    public int insRegister(Users users) {
        return usersMapper.insUsers(users);
    }
}
