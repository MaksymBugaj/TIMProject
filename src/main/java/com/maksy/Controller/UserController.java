package com.maksy.Controller;

import com.maksy.Entity.User;
import com.maksy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/doctors", method = RequestMethod.GET)
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public Collection<User> getDoctors() {
        return userService.getDoctors();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public User getUserById(@PathVariable("id") int id){
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/u/{email}", method = RequestMethod.GET)
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public User getUserByEmail(@PathVariable("email") String email){
        return userService.getUserByEmail(email);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable("id") int id){
        userService.removeUserById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertUser(@RequestBody User user){
        userService.insertUser(user);
    }
}
