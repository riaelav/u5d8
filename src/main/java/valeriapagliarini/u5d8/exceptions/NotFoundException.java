package valeriapagliarini.u5d8.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("La risorsa con id " + id + " non è stata trovata!");
    }
}
