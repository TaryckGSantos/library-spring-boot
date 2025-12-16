package io.github.taryckgsantos.libraryapi.service;

import io.github.taryckgsantos.libraryapi.controllers.dto.CadastroLivroDTO;
import io.github.taryckgsantos.libraryapi.exceptions.DatabaseException;
import io.github.taryckgsantos.libraryapi.exceptions.ResourceNotFoundException;
import io.github.taryckgsantos.libraryapi.model.Autor;
import io.github.taryckgsantos.libraryapi.model.Livro;
import io.github.taryckgsantos.libraryapi.repository.AutorRepository;
import io.github.taryckgsantos.libraryapi.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public Livro insert(Livro livro){
        try {
            return livroRepository.save(livro);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro de integridade ao inserir Livro.", e);
        }
    }

    @Transactional
    public Livro update(UUID id, CadastroLivroDTO livroDTO){
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        livro.setIsbn(livroDTO.getIsbn());
        livro.setTitulo(livroDTO.getTitulo());
        livro.setDataPublicacao(livroDTO.getDataPublicacao());
        livro.setGenero(livroDTO.getGenero());
        livro.setPreco(livroDTO.getPreco());

        Autor autor = autorRepository.findById(livroDTO.getAutor())
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado: " + livroDTO.getAutor()));
        livro.setAutor(autor);

        try {
            return livroRepository.save(livro);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro de integridade ao atualizar Livro. Id " + id, e);
        }
    }

    @Transactional
    public Livro findById(UUID id){
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public List<Livro> findAll(){
        return livroRepository.findAll();
    }

    @Transactional
    public void delete(UUID id){
        try {
            livroRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro de integridade ao deletar Livro. Id " + id, e);
        }
    }

    public Livro fromDTO(CadastroLivroDTO livroDTO){
        Autor autor = autorRepository.findById(livroDTO.getAutor())
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado: " + livroDTO.getAutor()));

        return new Livro(
                livroDTO.getIsbn(),
                livroDTO.getTitulo(),
                livroDTO.getDataPublicacao(),
                livroDTO.getGenero(),
                livroDTO.getPreco(),
                autor
        );
    }

    public List<Livro> search(String isbn, String titulo, io.github.taryckgsantos.libraryapi.model.GeneroLivro genero, UUID autorId) {

        boolean hasIsbn   = isbn != null && !isbn.isBlank();
        boolean hasTitulo = titulo != null && !titulo.isBlank();

        if (hasIsbn) {
            return livroRepository.findByIsbnContainingIgnoreCase(isbn.trim());
        }

        if (hasTitulo && genero != null && autorId != null) {
            return livroRepository.findByTituloContainingIgnoreCaseAndGeneroAndAutor_Id(
                    titulo.trim(), genero, autorId
            );
        }

        if (hasTitulo && genero != null) {
            return livroRepository.findByTituloContainingIgnoreCaseAndGenero(titulo.trim(), genero);
        }

        if (hasTitulo && autorId != null) {
            return livroRepository.findByTituloContainingIgnoreCaseAndAutor_Id(titulo.trim(), autorId);
        }

        if (genero != null && autorId != null) {
            return livroRepository.findByGeneroAndAutor_Id(genero, autorId);
        }

        if (hasTitulo) {
            return livroRepository.findByTituloContainingIgnoreCase(titulo.trim());
        }

        if (genero != null) {
            return livroRepository.findByGenero(genero);
        }

        if (autorId != null) {
            return livroRepository.findByAutor_Id(autorId);
        }

        return livroRepository.findAll();
    }
}
