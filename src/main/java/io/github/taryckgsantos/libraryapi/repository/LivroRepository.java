package io.github.taryckgsantos.libraryapi.repository;

import io.github.taryckgsantos.libraryapi.model.GeneroLivro;
import io.github.taryckgsantos.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    List<Livro> findByIsbnContainingIgnoreCase(String isbn);

    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    List<Livro> findByAutor_Id(UUID autorId);

    List<Livro> findByGenero(GeneroLivro genero);

    List<Livro> findByTituloContainingIgnoreCaseAndGenero(String titulo, GeneroLivro genero);

    List<Livro> findByTituloContainingIgnoreCaseAndAutor_Id(String titulo, UUID autorId);

    List<Livro> findByGeneroAndAutor_Id(GeneroLivro genero, UUID autorId);

    List<Livro> findByTituloContainingIgnoreCaseAndGeneroAndAutor_Id(String titulo, GeneroLivro genero, UUID autorId);

}
