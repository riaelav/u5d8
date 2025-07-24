package valeriapagliarini.u5d8.payloads;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BlogDTO(

        @NotEmpty(message = "La categoria è obbligatoria")
        String categoria,
        @NotEmpty(message = "Il titolo è obbligatorio")
        String titolo,
        @NotEmpty(message = "Il contenuto è obbligatorio")
        String contenuto,
        @NotNull(message = "Il tempo di lettura è obbligatorio")
        int tempoDiLettura,
        @NotNull(message = "L'id dell'autore è obbligatorio")
        Long authorId

) {
}

/*public class BlogDTO {
    private String categoria;
    private String titolo;
    private String contenuto;
    private int tempoDiLettura;
    private Long authorId;
}
*/