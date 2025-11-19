package io.github.taryckgsantos.libraryapi.service;

import io.github.taryckgsantos.libraryapi.controllers.dto.AutorDTO;
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
        return livroRepository.save(livro);
    }

    @Transactional
    public Livro update(UUID id, CadastroLivroDTO livroDTO){
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        livro.setIsbn(livroDTO.getIsbn());
        livro.setTitulo(livroDTO.getTitulo());
        livro.setDataPublicacao(livroDTO.getDataPublicacao());
        livro.setGenero(livroDTO.getGenero());
        livro.setPreco(livroDTO.getPreco());
        livro.setAutor(autorRepository.findById(livroDTO.getAutor()).orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado: " + livroDTO.getAutor())));
        return livroRepository.save(livro);
    }

    @Transactional
    public Livro findById(UUID id){
        return livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
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
            throw new DatabaseException(e.getMessage());
        }
    }

    public Livro fromDTO(CadastroLivroDTO livroDTO){
        return new Livro(livroDTO.getIsbn(), livroDTO.getTitulo(), livroDTO.getDataPublicacao(),
                         livroDTO.getGenero(), livroDTO.getPreco(),
                         autorRepository.findById(livroDTO.getAutor())
                         .orElseThrow(() -> new ResourceNotFoundException(livroDTO.getAutor())));
    }
}
