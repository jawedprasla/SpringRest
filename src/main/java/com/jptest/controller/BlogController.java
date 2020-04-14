package com.jptest.controller;

import com.jptest.dto.Blog;
import com.jptest.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BlogController {

   // BlogMockedData blogMockedData = BlogMockedData.getInstance();

    @Autowired
    BlogRepository blogRepository;

    @GetMapping("/blog")
    public List<Blog> index(){
        //return blogMockedData.fetchBlogs();
        return blogRepository.findAll();
    }

    @GetMapping("/blog/{id}")
    public Optional<Blog> show(@PathVariable String id){
        int blogId = Integer.parseInt(id);
       // return blogMockedData.getBlogById(blogId);
        return blogRepository.findById(blogId);
    }

    @PostMapping("/blog/search")
    public List<Blog> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        //return blogMockedData.searchBlogs(searchTerm);
        return blogRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    @PostMapping("/blog")
    public Blog create(@RequestBody Map<String, String> body){
       // int id = Integer.parseInt(body.get("id"));
        String title = body.get("title");
        String content = body.get("content");
        //return blogMockedData.createBlog(id, title, content);
        return blogRepository.save(new Blog(title, content));
    }

//    @PutMapping("/blog/{id}")
//    public Blog update(@PathVariable String id, @RequestBody Map<String, String> body){
//        int blogId = Integer.parseInt(id);
//        Optional<Blog> blog = blogRepository.findById(blogId);
//       // String title = body.get("title");
//       // String content = body.get("content");
//        //return blogMockedData.updateBlog(blogId, title, content);
//       // Blog blog = new Blog();
//        if(blog != null) {
//            //blog.setId(blogId);
//            blog.setTitle(body.get("title"));
//            blog.setContent(body.get("content"));
//            return blogRepository.save(blog);
//        }
//        return null;
//    }

    @DeleteMapping("/blog/{id}")
    public boolean delete(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        blogRepository.deleteById(blogId);
        return true;
       // return blogMockedData.delete(blogId);
    }

}
