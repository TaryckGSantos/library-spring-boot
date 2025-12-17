package io.github.taryckgsantos.libraryapi.repository.specs;

import io.github.taryckgsantos.libraryapi.model.GeneroLivro;
import io.github.taryckgsantos.libraryapi.model.Livro;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class LivroSpecs {

    public static Specification<Livro> isbnLike(String isbn) {
        if (isbn == null || isbn.isBlank()) return Specification.unrestricted();
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("isbn")), "%" + isbn.trim().toUpperCase() + "%");
    }

    public static Specification<Livro> tituloLike(String titulo) {
        if (titulo == null || titulo.isBlank()) return Specification.unrestricted();
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("titulo")), "%" + titulo.trim().toUpperCase() + "%");
    }

    public static Specification<Livro> generoEqual(GeneroLivro genero) {
        if (genero == null) return Specification.unrestricted();
        return (root, query, cb) -> cb.equal(root.get("genero"), genero);
    }

    public static Specification<Livro> autorEqual(UUID autorId) {
        if (autorId == null) return Specification.unrestricted();
        return (root, query, cb) -> cb.equal(root.get("autor").get("id"), autorId);
    }
}
