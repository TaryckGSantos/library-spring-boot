package io.github.taryckgsantos.libraryapi.controllers;

import io.github.taryckgsantos.libraryapi.controllers.dto.AutorDTO;
import io.github.taryckgsantos.libraryapi.model.Autor;
import io.github.taryckgsantos.libraryapi.model.Usuario;
import io.github.taryckgsantos.libraryapi.security.SecurityService;
import io.github.taryckgsantos.libraryapi.service.AutorService;
import io.github.taryckgsantos.libraryapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @Autowired
    private SecurityService securityService;

    @PostMapping
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Autor> insert(@Valid @RequestBody AutorDTO autor){
        Autor saved = autorService.fromDTO(autor);
        Usuario usuario = securityService.obterUsuarioLogado();
        saved.setUsuario(usuario);
        saved = autorService.insert(saved);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody AutorDTO autorDTO){
        UUID idAutor = UUID.fromString(id);
        Autor autor = autorService.update(idAutor, autorDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    public ResponseEntity<AutorDTO> findById(@PathVariable String id){
        UUID idAutor = UUID.fromString(id);
        Autor autor = autorService.findById(idAutor);
        return ResponseEntity.ok().body(new AutorDTO(autor));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    public ResponseEntity<List<AutorDTO>> findAllOrSearch(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String nacionalidade) {

        List<Autor> list = autorService.search(nome, nacionalidade);
        List<AutorDTO> listDTO = list.stream().map(AutorDTO::new).toList();
        return ResponseEntity.ok().body(listDTO);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> delete(@PathVariable String id){
        UUID idAutor = UUID.fromString(id);
        autorService.delete(idAutor);
        return ResponseEntity.noContent().build();
    }
}
