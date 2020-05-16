package com.sqw.controller;

import com.sqw.pojo.Users;
import com.sqw.service.UsersService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UsersController {

    @Resource
    private UsersService usersServiceImpl;

    @RequestMapping("register")
    public String register(Users users, MultipartFile file, HttpServletRequest request){

        //文件名
        String fileName = UUID.randomUUID().toString()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //文件的路径
        String path= request.getServletContext().getRealPath("images")+"/"+fileName;

        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //只能获取到webapps中的文件夹内容
        users.setPhoto(fileName);
        int index = usersServiceImpl.insRegister(users);
        if(index>0){
            return "/main.jsp";
        }else{
            return "redirect:/register.jsp";
        }
    }

}
