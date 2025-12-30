package io.github.taryckgsantos.libraryapi.controllers;

import io.github.taryckgsantos.libraryapi.controllers.dto.UsuarioDTO;
import io.github.taryckgsantos.libraryapi.model.Usuario;
import io.github.taryckgsantos.libraryapi.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> insert(@Valid @RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioService.fromDTO(usuarioDTO);
        Usuario saved = usuarioService.insert(usuario);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
