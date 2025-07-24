package valeriapagliarini.u5d8.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewAuthorDTO(
        @NotEmpty(message = "Il nome è obbligatorio!")
        @Size(min = 2, max = 40, message = "Il nome deve essere tra 2 e 40 caratteri")
        String nome,

        @NotEmpty(message = "Il cognome è obbligatorio!")
        @Size(min = 2, max = 40, message = "Il cognome deve essere tra 2 e 40 caratteri")
        String cognome,

        @NotEmpty(message = "L'indirizzo email è obbligatorio!")
        @Email(message = "L'indirizzo email non è valido")
        String email,


        @NotNull(message = "La data di nascita è obbligatoria")
        LocalDate dataDiNascita
) {
}
