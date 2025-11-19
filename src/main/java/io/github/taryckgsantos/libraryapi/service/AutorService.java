package io.github.taryckgsantos.libraryapi.service;

import io.github.taryckgsantos.libraryapi.controllers.dto.AutorDTO;
import io.github.taryckgsantos.libraryapi.exceptions.DatabaseException;
import io.github.taryckgsantos.libraryapi.exceptions.ObjectNotFoundException;
import io.github.taryckgsantos.libraryapi.exceptions.ResourceNotFoundException;
import io.github.taryckgsantos.libraryapi.model.Autor;
import io.github.taryckgsantos.libraryapi.repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public Autor insert(Autor autor){
        return autorRepository.save(autor);
    }

    @Transactional
    public Autor update(UUID id, AutorDTO autorDTO){
        Autor autor = autorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        autor.setNome(autorDTO.getNome());
        autor.setNacionalidade(autorDTO.getNacionalidade());
        autor.setDataNascimento(autorDTO.getDataNascimento());
        return autorRepository.save(autor);
    }

    @Transactional
    public Autor findById(UUID id){
        return autorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public List<Autor> findAll(){
        return autorRepository.findAll();
    }

    @Transactional
    public void delete(UUID id){
        try {
            autorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Autor fromDTO(AutorDTO autorDTO){
        return new Autor(autorDTO.getNome(), autorDTO.getDataNascimento(), autorDTO.getNacionalidade());
    }
}
