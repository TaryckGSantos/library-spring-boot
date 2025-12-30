package io.github.taryckgsantos.libraryapi.model;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_usuario")
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String login;

    private String senha;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "varchar[]")
    private List<String> roles;

    public Usuario(String login, String senha, List<String> roles){
        this.login = login;
        this.senha = senha;
        this.roles = roles;
    }

}
