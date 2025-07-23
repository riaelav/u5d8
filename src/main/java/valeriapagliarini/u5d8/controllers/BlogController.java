package valeriapagliarini.u5d8.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import valeriapagliarini.u5d8.entities.Blog;
import valeriapagliarini.u5d8.payloads.BlogPayload;
import valeriapagliarini.u5d8.services.BlogsService;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogsService blogsService;

    // GET
    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogsService.getBlogs();
    }

    // GET /blogs/un singolo blog post per ID
    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable Long id) {
        return blogsService.findById(id);
    }

    // POST crea un nuovo blog post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blog createBlog(@RequestBody BlogPayload payload) {
        return blogsService.save(payload);
    }

    // PUT aggiorna un blog post esistente
    @PutMapping("/{id}")
    public Blog updateBlog(@PathVariable Long id, @RequestBody Blog blog) {
        return blogsService.findByIdAndUpdate(id, blog);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable Long id) {
        blogsService.findByIdAndDelete(id);
    }
}
