package io.github.taryckgsantos.libraryapi.repository;

import io.github.taryckgsantos.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID>, JpaSpecificationExecutor<Livro> {
    boolean existsByIsbnIgnoreCase(String isbn);

    boolean existsByIsbnIgnoreCaseAndIdNot(String isbn, UUID id);
}
