package com.example.week6hw28.Service;

import com.example.week6hw28.ApiException.ApiException;
import com.example.week6hw28.Model.MyUser;
import com.example.week6hw28.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserService {
    private final MyUserRepository myUserRepository;

    public MyUser getUser(Integer userId){
        MyUser myUser = myUserRepository.findMyUserById(userId);
        if (myUser==null)
            throw new ApiException("user not found");
        return myUser;
    }
    public void addUser(MyUser myUser){
        myUser.setRole("CUSTOMER");
        myUser.setPassword(new BCryptPasswordEncoder().encode(myUser.getPassword()));
        myUserRepository.save(myUser);
    }
    public void updateUser(MyUser myUser,MyUser old){
        MyUser u1= myUserRepository.findMyUserById(myUser.getId());
        MyUser u2= myUserRepository.findMyUserByUsername(old.getUsername());
        if (u1==null)
            throw new ApiException("user not found");
        if (u2!=null)
            throw new ApiException("username taken");
        u1.setUsername(old.getUsername());
        u1.setPassword(new BCryptPasswordEncoder().encode(old.getPassword()));
        myUserRepository.save(u1);
    }
    public void deleteUser(MyUser myUser){
        MyUser old= myUserRepository.findMyUserById(myUser.getId());
        if (old==null)
            throw new ApiException("user not found");
        myUserRepository.delete(old);
    }


}
