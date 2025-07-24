package valeriapagliarini.u5d8.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import valeriapagliarini.u5d8.entities.Author;
import valeriapagliarini.u5d8.entities.Blog;
import valeriapagliarini.u5d8.exceptions.NotFoundException;
import valeriapagliarini.u5d8.payloads.BlogDTO;
import valeriapagliarini.u5d8.repositories.BlogsRepository;

import java.util.List;

@Service
public class BlogsService {

    @Autowired
    private BlogsRepository blogsRepository;
    @Autowired
    private AuthorsService authorsService;

    // CREATE
    public Blog save(BlogDTO payload) {
        Author author = authorsService.findById(payload.authorId());
        Blog blog = new Blog(payload.categoria(), payload.titolo(), "https://picsum.photos/200/300", payload.contenuto(), payload.tempoDiLettura(), author);
        return blogsRepository.save(blog);
    }

    // READ ALL
    public List<Blog> getBlogs() {
        return blogsRepository.findAll();
    }

    // READ BY ID
    public Blog findById(Long id) {
        return blogsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    // DELETE
    public void findByIdAndDelete(Long id) {
        Blog found = this.findById(id);
        blogsRepository.delete(found);
    }

    // UPDATE
    public Blog findBlogByIdAndUpdate(long blogId, BlogDTO payload) {
        //riuso il metodo findBlogById
        Blog found = findById(blogId);

        found.setCategoria(payload.categoria());
        found.setTitolo(payload.titolo());
        found.setContenuto(payload.contenuto());
        found.setTempoDiLettura(payload.tempoDiLettura());

        Blog updatedBlog = blogsRepository.save(found);

        return updatedBlog;
    }
}