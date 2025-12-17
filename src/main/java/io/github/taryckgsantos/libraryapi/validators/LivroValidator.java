package io.github.taryckgsantos.libraryapi.validators;

import io.github.taryckgsantos.libraryapi.exceptions.DatabaseException;
import io.github.taryckgsantos.libraryapi.model.Livro;
import io.github.taryckgsantos.libraryapi.repository.LivroRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivroValidator {

    private final LivroRepository livroRepository;

    public LivroValidator(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public void validar(Livro livro) {
        if (existeIsbn(livro)) {
            throw new DatabaseException("ISBN já existente!");
        }
    }

    private boolean existeIsbn(Livro livro) {
        if (livro.getId() == null) {
            return livroRepository.existsByIsbnIgnoreCase(livro.getIsbn());
        }
        return livroRepository.existsByIsbnIgnoreCaseAndIdNot(livro.getIsbn(), livro.getId());
    }
}
