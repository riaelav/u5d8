package valeriapagliarini.u5d8.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "blogs")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    @Column(name = "tempo_di_lettura")
    private int tempoDiLettura;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Blog(String categoria, String titolo, String cover, String contenuto, int tempoDiLettura, Author author) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.cover = cover;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.author = author;
    }
}
