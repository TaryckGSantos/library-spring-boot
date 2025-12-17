package io.github.taryckgsantos.libraryapi.repository.specs;

import io.github.taryckgsantos.libraryapi.model.Autor;
import org.springframework.data.jpa.domain.Specification;

public class AutorSpecs {

    public static Specification<Autor> nomeLike(String nome) {
        if (nome == null || nome.isBlank()) return Specification.unrestricted();
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("nome")), "%" + nome.trim().toUpperCase() + "%");
    }

    public static Specification<Autor> nacionalidadeLike(String nacionalidade) {
        if (nacionalidade == null || nacionalidade.isBlank()) return Specification.unrestricted();
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("nacionalidade")), "%" + nacionalidade.trim().toUpperCase() + "%");
    }
}
