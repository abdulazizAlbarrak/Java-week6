package com.example.week6hw27.Service;

import com.example.week6hw27.Model.Blog;
import com.example.week6hw27.Model.MyUser;
import com.example.week6hw27.Repository.BlogRepository;
import com.example.week6hw27.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MyUserService {
    private final MyUserRepository myUserRepository;
    private final BlogRepository blogRepository;

    public MyUser getUser(Integer userId){
        return myUserRepository.findMyUserById(userId);
    }
    public void addUser(MyUser myUser){
        String hash= new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
        myUserRepository.save(myUser);
    }
    public void updateUser(Integer userId, MyUser myUser){
        MyUser old = myUserRepository.findMyUserById(userId);
        if (old == null)
            throw new RuntimeException("wrong id");
        old.setPassword(new BCryptPasswordEncoder().encode(myUser.getPassword()));
        old.setUsername(myUser.getUsername());
        myUserRepository.save(old);
    }
    public void deleteUser(Integer myUserId,Integer userId){
        MyUser myUser = myUserRepository.findMyUserById(userId);
        MyUser myUser1 = myUserRepository.findMyUserById(myUserId);
        if (myUser==null || myUser1==null)
            throw new RuntimeException("id not found");
        if (myUser.getId()!=myUser1.getId())
            throw new RuntimeException("not authorized");
        myUserRepository.delete(myUser);
    }

    public Set<Blog> getBlogs(Integer userId){
        MyUser myUser =myUserRepository.findMyUserById(userId);
        if (myUser==null)
            throw new RuntimeException("id not found");
        return blogRepository.findBlogsByMyUserId(myUser.getId());
    }
}
