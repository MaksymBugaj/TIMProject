package com.maksy.Service;

import com.maksy.Dao.UserDao;
import com.maksy.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Collection<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public User getUserById(String id){
        return this.userDao.getUserById(id);
    }

    public void removeUserById(String id) {
        this.userDao.removeUserById(id);
    }

    public void updateUser(User user){
        this.userDao.updateUser(user);
    }

    public void insertUser(User user) {
        this.userDao.insertUser(user);
    }

    public Collection<User> getDoctors() {
        return userDao.getDoctors();
    }

    public User getUserByEmail(String email) {
        return this.userDao.getUserByEmail(email);
    }
}
