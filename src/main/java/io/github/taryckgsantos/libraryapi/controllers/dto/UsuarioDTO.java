package io.github.taryckgsantos.libraryapi.controllers.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O campo \"login\" é obrigatório")
    private String login;

    @NotBlank(message = "O campo \"senha\" é obrigatório")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha;

    @Email(message = "Email inválido")
    @NotBlank(message = "O campo \"email\" é obrigatório")
    private String email;

    private List<String> roles;

}
