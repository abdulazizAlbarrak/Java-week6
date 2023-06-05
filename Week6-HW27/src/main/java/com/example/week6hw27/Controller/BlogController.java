package com.example.week6hw27.Controller;

import com.example.week6hw27.Model.Blog;
import com.example.week6hw27.Model.MyUser;
import com.example.week6hw27.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/get")
    public ResponseEntity getAllBlogs(){
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }
    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal MyUser myUser,@Valid @RequestBody Blog blog){
        blogService.addBlog(myUser.getId(), blog);
        return ResponseEntity.status(200).body("blog added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer id, @Valid @RequestBody Blog blog){
        blogService.updateBlog(myUser.getId(), id,blog);
        return ResponseEntity.status(200).body("blog updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer id){
        blogService.deleteBlog(myUser.getId(), id);
        return ResponseEntity.status(200).body("blog deleted");
    }
    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }
}
