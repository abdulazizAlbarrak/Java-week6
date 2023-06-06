package com.example.week6hw28.Controller;

import com.example.week6hw28.Model.MyUser;
import com.example.week6hw28.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class MyUserController {
    private final MyUserService myUserService;

    @GetMapping("/get")
    public ResponseEntity getUser(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(myUserService.getUser(myUser.getId()));
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody MyUser myUser){
        myUserService.addUser(myUser);
        return ResponseEntity.status(200).body("user added");
    }
    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal MyUser myUser, @Valid @RequestBody MyUser newUser){
        myUserService.updateUser(myUser, newUser);
        return ResponseEntity.status(200).body("user updated");
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal MyUser myUser){
        myUserService.deleteUser(myUser);
        logout();
        return ResponseEntity.status(200).body("user deleted");
    }
    @GetMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("logout");
    }
}
