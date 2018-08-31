package com.maksy.Dao;

import com.google.firebase.database.*;
import com.maksy.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDao {

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private Map<String,User> users = new HashMap<String,User>();
    public UserDao() {

    }

    private void initUsers(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        Query query = databaseReference.orderByChild("email");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String id = (String) dataSnapshot1.child("id").getValue();
                    String firstname = (String) dataSnapshot1.child("firstname").getValue();
                    String surname = (String) dataSnapshot1.child("surname").getValue();
                    String PESEL = (String) dataSnapshot1.child("pesel").getValue();
                    String sex = (String) dataSnapshot1.child("sex").getValue();
                    String email = (String) dataSnapshot1.child("email").getValue();
                    String phone = (String) dataSnapshot1.child("phone").getValue();
                    String password = (String) dataSnapshot1.child("password").getValue();
                    String type = (String) dataSnapshot1.child("type").getValue();
                    User user = new User(id,firstname,surname,PESEL,sex,email,phone,password,type);
                    users.put(user.getId(),user);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Collection<User> getAllUsers(){
        initUsers();
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

    public User getUserById(String id){
        return this.users.get(id);
    }

    public void removeUserById(String id) {
        this.users.remove(id);
    }

    public void updateUser(User user){
       /* User user1 = users.get(user.getId());
        user1.setFirstname(user.getFirstname());
        user1.setSurname(user.getSurname());
        user1.setPESEL(user.getPESEL());
        user1.setSex(user.getSex());
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
        user1.setPassword(user.getPassword());
        user1.setType(user.getType());
        users.put(user.getId(),user);*/
    }

    public void insertUser(User user) {
        String userId = databaseReference.push().getKey();
        User user1 = new User(userId,user.getFirstname(),user.getSurname(),user.getPESEL(),user.getSex(),user.getEmail(),user.getPhone(),user.getPassword(),user.getType());
        databaseReference.child(userId).setValue(user1);
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
