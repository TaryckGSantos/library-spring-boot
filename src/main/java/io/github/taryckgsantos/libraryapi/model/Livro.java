package io.github.taryckgsantos.libraryapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_livro")
@Data // Getter, Setter, toString, HashCode e Equals
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 20, nullable = false)
    private String isbn;

    @Column(length = 150, nullable = false)
    private String titulo;

    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING) // Quando não uso numerais lá no enum, coloco essa @ para dizer qual é o tipo do enum
    @Column(length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(precision = 18, scale = 2, nullable = false)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    public Livro(String isbn, String titulo, LocalDate dataPublicacao,
                 GeneroLivro genero, BigDecimal preco, Autor autor){
        this.isbn = isbn;
        this.titulo = titulo;
        this.dataPublicacao = dataPublicacao;
        this.genero = genero;
        this.preco = preco;
        this.autor = autor;
    }
}
