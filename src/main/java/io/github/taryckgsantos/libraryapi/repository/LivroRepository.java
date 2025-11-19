package io.github.taryckgsantos.libraryapi.repository;

import io.github.taryckgsantos.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
