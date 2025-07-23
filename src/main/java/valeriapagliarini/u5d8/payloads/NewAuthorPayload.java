package valeriapagliarini.u5d8.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class NewAuthorPayload {

    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
}
