package com.example.week6hw28.Service;

import com.example.week6hw28.Model.MyUser;
import com.example.week6hw28.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final MyUserRepository myUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user=myUserRepository.findMyUserByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException("Wrong username or password");
        }

        return user;
    }
}
