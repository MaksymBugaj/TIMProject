package com.maksy.Dao;

import com.maksy.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDao {

    private static Map<Integer, User> users;
//fixme kick this shit out
    static {
        users = new HashMap<Integer, User>(){
            {
                put(1, new User(1,"Marcin","Borowski"));
                put(2, new User(2,"Maksym","Bugaj"));
                put(3, new User(3,"Mariusz","Byler"));
                put(4, new User(4,"Łukasz","Budner"));
            }
        };
    }

    public Collection<User> getAllUsers(){
        return this.users.values();
    }

    public User getUserById(int id){
        return this.users.get(id);
    }

    public void removeUserById(int id) {
        this.users.remove(id);
    }

    public void updateUser(User user){
        User user1 = users.get(user.getId());
        user1.setName(user.getName());
        user1.setSurname(user.getSurname());
        users.put(user.getId(),user);
    }

    public void insertUser(User user) {
        this.users.put(user.getId(),user);
    }
}
