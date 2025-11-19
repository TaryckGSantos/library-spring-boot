package io.github.taryckgsantos.libraryapi.controllers.dto;

import io.github.taryckgsantos.libraryapi.model.GeneroLivro;
import io.github.taryckgsantos.libraryapi.model.Livro;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CadastroLivroDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String isbn;
    private String titulo;
    private LocalDate dataPublicacao;
    private GeneroLivro genero;
    private BigDecimal preco;
    private UUID autor;

    public CadastroLivroDTO(Livro livro){

        super();
        this.id = livro.getId();
        this.isbn = livro.getIsbn();
        this.titulo = livro.getTitulo();
        this.dataPublicacao = livro.getDataPublicacao();
        this.genero = livro.getGenero();
        this.preco = livro.getPreco();
        this.autor = livro.getAutor().getId();
    }
}
