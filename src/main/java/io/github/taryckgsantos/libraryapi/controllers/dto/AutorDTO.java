package io.github.taryckgsantos.libraryapi.controllers.dto;

import io.github.taryckgsantos.libraryapi.model.Autor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AutorDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private String nacionalidade;

    public AutorDTO(Autor autor) {
        super();
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.dataNascimento = autor.getDataNascimento();
        this.nacionalidade = autor.getNacionalidade();
    }
}
