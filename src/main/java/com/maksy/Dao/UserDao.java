package com.maksy.Dao;

import com.maksy.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDao {

    private static Map<Integer, User> users;
//fixme kick this shit out
    static {
        users = new HashMap<Integer, User>(){
            {
                put(1, new User(1,"Marcin","Borowski","PESEL1","male","email1","phone1","password1","1"));
                put(2, new User(2,"Maksym","Bugaj","PESEL2","male","email2","phone2","password2","1"));
                put(3, new User(3,"Mariusz","Byler","PESEL3","male","email3","phone3","password3","0"));
                put(4, new User(4,"Łukasz","Budner","PESEL4","male","email4","phone4","password4","0"));
                put(5, new User(404,"Łukasz","Budner","PESEL5","female","email5","phone5","password5","1"));
            }
        };
    }

    public Collection<User> getAllUsers(){
        return this.users.values();
    }

    public Collection<User> getDoctors(){
        List<User> tempUser = new ArrayList<>();
        for(User user : users.values()){
            if(user.getType().equals("1")){
                tempUser.add(user);
            }
        }
        return  tempUser;

    }

    public User getUserById(int id){
        return this.users.get(id);
    }

    public void removeUserById(int id) {
        this.users.remove(id);
    }

    public void updateUser(User user){
        User user1 = users.get(user.getId());
        user1.setFirstname(user.getFirstname());
        user1.setSurname(user.getSurname());
        user1.setPESEL(user.getPESEL());
        user1.setSex(user.getSex());
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
        user1.setPassword(user.getPassword());
        user1.setType(user.getType());
        users.put(user.getId(),user);
    }

    public void insertUser(User user) {
        this.users.put(user.getId(),user);
    }

    public User getUserByEmail(String email) {
        User tempUser = new User();
        for(User user : users.values()){
            if(user.getEmail().equals(email)){
                tempUser = user;
            }
        }
        return  tempUser;
    }
}
