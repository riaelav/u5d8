package valeriapagliarini.u5d8.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import valeriapagliarini.u5d8.entities.Author;
import valeriapagliarini.u5d8.exceptions.BadRequestException;
import valeriapagliarini.u5d8.exceptions.NotFoundException;
import valeriapagliarini.u5d8.payloads.NewAuthorDTO;
import valeriapagliarini.u5d8.repositories.AuthorsRepository;

@Service
@Slf4j
public class AuthorsService {

    @Autowired
    private AuthorsRepository authorsRepository;

    //SAVE
    public Author save(NewAuthorDTO payload) {
        // controllo che l'email non sia già in uso
        authorsRepository.findByEmail(payload.email()).ifPresent(author -> {
            throw new BadRequestException("L'email " + author.getEmail() + " è già in uso!");
        });
        // creo l'autore
        Author newAuthor = new Author(payload.nome(), payload.cognome(), payload.email(), payload.dataDiNascita());
        // avatar
        newAuthor.setAvatar("https://ui-avatars.com/api/?name=" + payload.nome() + "+" + payload.cognome());

        // salvataggio
        Author saved = authorsRepository.save(newAuthor);


        log.info("Autore con ID " + saved.getId() + " salvato correttamente.");

        return saved;
    }

    // FIND ALL CON PAGINAZIONE
    public Page<Author> findAll(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return authorsRepository.findAll(pageable);
    }

    // FIND BY ID
    public Author findById(Long id) {
        return authorsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    //FIND AND UPDATE
    public Author findByIdAndUpdate(Long id, NewAuthorDTO payload) {
        Author found = this.findById(id);
        //controllo che la mail non sia già in uso
        if (!found.getEmail().equals(payload.email())) {
            authorsRepository.findByEmail(payload.email()).ifPresent(author -> {
                throw new BadRequestException("L'email " + author.getEmail() + " è già in uso!");
            });
        }

        found.setNome(payload.nome());
        found.setCognome(payload.cognome());
        found.setEmail(payload.email());
        found.setDataDiNascita(payload.dataDiNascita());
        found.setAvatar("https://ui-avatars.com/api/?name=" + payload.nome() + "+" + payload.cognome());

        Author updated = authorsRepository.save(found);
        log.info("Autore con ID " + id + " aggiornato.");

        return updated;
    }

    //DELETE
    public void findByIdAndDelete(Long id) {
        Author found = this.findById(id);
        authorsRepository.delete(found);
        log.info("Autore con ID " + id + " eliminato.");
    }
}
