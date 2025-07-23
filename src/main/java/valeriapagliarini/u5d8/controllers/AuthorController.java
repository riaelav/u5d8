package valeriapagliarini.u5d8.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import valeriapagliarini.u5d8.entities.Author;
import valeriapagliarini.u5d8.payloads.NewAuthorPayload;
import valeriapagliarini.u5d8.services.AuthorsService;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorsService authorsService;

    //GET http://localhost:3001/authors
    @GetMapping
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String sortBy) {
        return this.authorsService.findAll(page, size, sortBy);
    }

    //POST http://localhost:3001/authors (+ payload)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody NewAuthorPayload body) {
        return this.authorsService.save(body);
    }


    // GET http://localhost:3001/authors/id
    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable Long authorId) {
        return this.authorsService.findById(authorId);
    }

    //  PUT http://localhost:3001/authors/id (+ payload)
    @PutMapping("/{authorId}")
    public Author findAuthorByIdAndUpdate(@RequestBody NewAuthorPayload body, @PathVariable Long authorId) {
        return this.authorsService.findByIdAndUpdate(authorId, body);
    }

    // DELETE http://localhost:3001/authors/id
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void findAuthorByIdAndDelete(@PathVariable Long authorId) {
        this.authorsService.findByIdAndDelete(authorId);
    }
}

