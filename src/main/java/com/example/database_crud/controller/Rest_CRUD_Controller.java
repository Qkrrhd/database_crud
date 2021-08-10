package com.example.database_crud.controller;

import com.example.database_crud.dto.UserDto;
import com.example.database_crud.service.UserService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class Rest_CRUD_Controller {

    @Autowired
    UserService userService;
    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public List<UserDto> getUsers() {
        System.err.println("UserController getUsers");
        List<UserDto> users = userService.select_all();
        return users;
    }

    @GetMapping(path = "/user/find/{id}")
    public UserDto getOneUser(@PathVariable("id") String id) {
        System.err.println("UserController getOneUser");
        return userService.select_one(id);
    }


    @PostMapping("/user/create")
    public UserDto createUser(@ModelAttribute UserDto user) {
        System.err.println("UserController createUser");
        UserDto res = new UserDto();
        res = userService.createUser(user);
        return res;
    }


    @PutMapping("/user/update/{id}")
    public UserDto updateUser(@PathVariable("id") String id,
                              @ModelAttribute UserDto user) {
        System.err.println("UserController updateUser");
        userService.updateUser(id, user);
        return userService.select_one(id);
    }


    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        System.err.println("UserController deleteUser");
        userService.deleteUser(id);
    }


}
