package com.rest.webservices.restfulwebservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserResource {

    @Autowired
    UserDaoService userService;

    @GetMapping("/users")
    public List<User> retriveAllUsers(){
       return userService.findAll();
    }
    @GetMapping("/users/{id}")
    public User retriveUser(@PathVariable int id){
        return userService.findId(id);
    }
}
