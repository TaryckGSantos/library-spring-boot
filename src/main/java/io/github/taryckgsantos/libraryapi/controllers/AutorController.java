package io.github.taryckgsantos.libraryapi.controllers;

import io.github.taryckgsantos.libraryapi.controllers.dto.AutorDTO;
import io.github.taryckgsantos.libraryapi.model.Autor;
import io.github.taryckgsantos.libraryapi.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<Autor> insert(@RequestBody AutorDTO autor){
        Autor saved = autorService.fromDTO(autor);
        saved = autorService.insert(saved);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody AutorDTO autorDTO){
        UUID idAutor = UUID.fromString(id);
        Autor autor = autorService.update(idAutor, autorDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AutorDTO> findById(@PathVariable String id){
        UUID idAutor = UUID.fromString(id);
        Autor autor = autorService.findById(idAutor);
        return ResponseEntity.ok().body(new AutorDTO(autor));
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> findAll(){
        List<Autor> list = autorService.findAll();
        List<AutorDTO> listDTO = list.stream().map(AutorDTO::new).toList();
        return ResponseEntity.ok().body(listDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        UUID idAutor = UUID.fromString(id);
        autorService.delete(idAutor);
        return ResponseEntity.noContent().build();
    }
}
