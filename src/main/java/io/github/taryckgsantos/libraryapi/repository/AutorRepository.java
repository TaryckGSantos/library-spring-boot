package io.github.taryckgsantos.libraryapi.repository;

import io.github.taryckgsantos.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
    List<Autor> findByNomeContainingIgnoreCase(String nome);

    List<Autor> findByNacionalidadeContainingIgnoreCase(String nacionalidade);

    List<Autor> findByNomeContainingIgnoreCaseAndNacionalidadeContainingIgnoreCase(
            String nome,
            String nacionalidade
    );

}
