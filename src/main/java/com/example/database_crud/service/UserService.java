package com.example.database_crud.service;

import com.example.database_crud.dao.UserDao;
import com.example.database_crud.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public  List<UserDto> select_all(){
        List<UserDto> list =userDao.select_all();
        for (UserDto user : list) {
            System.out.println(user.toString());
        }
        return list;
    }


    public UserDto select_one(String id) {
        UserDto user = new UserDto();
        try {
            user = userDao.select_one(id);
            System.out.println(user);
        }catch (Exception e){

            System.out.println("User not found");
        }

        return user;
    }

    public void updateUser(String id, UserDto user) {
        System.err.println("UserService createUser");
        System.out.println(user.toString());


        UserDto find_user = new UserDto();
        try {
            user = userDao.select_one(id);
            System.out.println(user);
        }catch (Exception e){

            find_user.setName(user.getName());
            find_user.setAge(user.getAge());
            find_user.setPasswd(user.getPasswd());
            userDao.update(find_user);
            System.out.println("User not found");
        }

    }
    public void deleteUser(String id) {
        System.err.println("UserService deleteUser");
        try {
            userDao.delete(id);
        }catch (Exception e){
            System.out.println("not found User");
        }

    }

    public UserDto createUser(UserDto user) {
        System.err.println("UserService createUser");
        System.out.println(user.toString());
        userDao.insert(user);
        return user;
    }

}
