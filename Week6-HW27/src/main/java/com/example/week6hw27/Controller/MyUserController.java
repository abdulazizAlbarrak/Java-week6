package com.example.week6hw27.Controller;

import com.example.week6hw27.Model.MyUser;
import com.example.week6hw27.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
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
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer id,@Valid @RequestBody MyUser myUserNew){
        myUserService.updateUser(id,myUserNew);
        return ResponseEntity.status(200).body("user updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer id){
        myUserService.deleteUser(myUser.getId(),id);
        return ResponseEntity.status(200).body("user deleted");
    }
    @GetMapping("/get-blogs")
    public ResponseEntity getBlogs(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(myUserService.getBlogs(myUser.getId()));
    }
    @GetMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("logout");
    }
}
