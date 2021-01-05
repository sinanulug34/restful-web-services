package com.rest.webservices.restfulwebservices;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        @PostMapping("/users")
        public ResponseEntity<Object> createUser(@RequestBody User user){
            User savedUser=userService.save(user);

            URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
            return ResponseEntity.created(location).build();
        }

    //https://lankydan.dev/2017/04/02/simple-spring-boot-post
}
