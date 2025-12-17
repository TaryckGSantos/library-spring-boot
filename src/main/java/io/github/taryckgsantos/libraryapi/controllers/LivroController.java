package io.github.taryckgsantos.libraryapi.controllers;

import io.github.taryckgsantos.libraryapi.controllers.dto.CadastroLivroDTO;
import io.github.taryckgsantos.libraryapi.model.GeneroLivro;
import io.github.taryckgsantos.libraryapi.model.Livro;
import io.github.taryckgsantos.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Livro> insert(@Valid @RequestBody CadastroLivroDTO livroDTO){
        Livro saved = livroService.fromDTO(livroDTO);
        saved = livroService.insert(saved);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody CadastroLivroDTO livroDTO){
        UUID livroId = UUID.fromString(id);
        Livro livro = livroService.update(livroId, livroDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CadastroLivroDTO> findById(@PathVariable String id){
        UUID livroId = UUID.fromString(id);
        Livro livro = livroService.findById(livroId);
        return ResponseEntity.ok().body(new CadastroLivroDTO(livro));
    }

    @GetMapping
    public ResponseEntity<List<CadastroLivroDTO>> findAllOrSearch(
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) GeneroLivro genero,
            @RequestParam(required = false) UUID autorId ) {

        List<Livro> list = livroService.search(isbn, titulo, genero, autorId);
        List<CadastroLivroDTO> listDTO = list.stream().map(CadastroLivroDTO::new).toList();
        return ResponseEntity.ok().body(listDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Livro> delete(@PathVariable String id){
        UUID livroId = UUID.fromString(id);
        livroService.delete(livroId);
        return ResponseEntity.noContent().build();
    }
}
