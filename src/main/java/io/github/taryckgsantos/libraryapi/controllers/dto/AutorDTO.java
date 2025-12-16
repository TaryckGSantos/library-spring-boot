package io.github.taryckgsantos.libraryapi.controllers.dto;

import io.github.taryckgsantos.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Campo \"nome\" obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotNull(message = "Campo \"data de nascimento\" obrigatório")
    @Past(message = "Data inválida")
    private LocalDate dataNascimento;

    @NotBlank(message = "Campo \"nacionalidade\" obrigatório")
    @Size(min = 2, max = 50, message = "Nacionalidade deve ter entre 2 e 50 caracteres")
    private String nacionalidade;

    public AutorDTO(Autor autor) {
        super();
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.dataNascimento = autor.getDataNascimento();
        this.nacionalidade = autor.getNacionalidade();
    }
}
