package valeriapagliarini.u5d8.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import valeriapagliarini.u5d8.entities.Author;
import valeriapagliarini.u5d8.entities.Blog;
import valeriapagliarini.u5d8.exceptions.NotFoundException;
import valeriapagliarini.u5d8.payloads.BlogPayload;
import valeriapagliarini.u5d8.repositories.BlogsRepository;

import java.util.List;

@Service
public class BlogsService {

    @Autowired
    private BlogsRepository blogsRepository;
    @Autowired
    private AuthorsService authorsService;

    // CREATE
    public Blog save(BlogPayload payload) {
        Author author = authorsService.findById(payload.getAuthorId());
        Blog blog = new Blog(payload.getCategoria(), payload.getTitolo(), "https://picsum.photos/200/300", payload.getContenuto(), payload.getTempoDiLettura(), author);
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
    public Blog findByIdAndUpdate(Long id, Blog body) {
        Blog found = this.findById(id);

        found.setCover(body.getCover());
        found.setCategoria(body.getCategoria());
        found.setContenuto(body.getContenuto());
        found.setTempoDiLettura(body.getTempoDiLettura());
        found.setTitolo(body.getTitolo());
        return blogsRepository.save(found);
    }
}