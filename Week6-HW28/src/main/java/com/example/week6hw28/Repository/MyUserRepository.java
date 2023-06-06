package com.example.week6hw28.Repository;

import com.example.week6hw28.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Integer> {
    public MyUser findMyUserById(Integer userId);
    public MyUser findMyUserByUsername(String username);
}
