package io.github.taryckgsantos.libraryapi.controllers.dto;

import io.github.taryckgsantos.libraryapi.model.GeneroLivro;
import io.github.taryckgsantos.libraryapi.model.Livro;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "O campo \"isbn\" é obrigatório")
    @Size(min = 10, max = 20, message = "O isbn deve ter entre 10 e 20 caracteres")
    private String isbn;

    @NotBlank(message = "O campo \"título\" é obrigatório")
    @Size(min = 2, max = 150, message = "O título deve ter entre 2 e 150 caracteres")
    private String titulo;

    @NotNull(message = "O campo data de \"publicação\" é obrigatório")
    @PastOrPresent(message = "A data de publicação não pode ser futura")
    private LocalDate dataPublicacao;

    @NotNull(message = "O campo \"gênero\" é obrigatório")
    private GeneroLivro genero;

    @NotNull(message = "O campo \"preço\" é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "O campo \"autor\" é obrigatório")
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
