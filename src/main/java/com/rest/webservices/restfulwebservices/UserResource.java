package com.rest.webservices.restfulwebservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import javax.validation.Valid;
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
    public Resource<User> retriveUser(@PathVariable int id){
        User user = userService.findId(id);
        if (user==null)
        throw new UserNotFoundException("id -" + id);

        Resource<User> resource = new Resource<User>(user);

        ControllerLinkBuilder link =
                linkTo(methodOn(this.getClass()).retriveAllUsers());
        resource.add(link.withRel("all-users").withType("GET"));
        return resource;
    }
        @PostMapping("/users")
        public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
            User savedUser=userService.save(user);
            URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = userService.deleteById(id);
        if (user==null)
            throw new UserNotFoundException("id -" + id);
    }
}
