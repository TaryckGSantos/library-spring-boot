package io.github.taryckgsantos.libraryapi.service;

import io.github.taryckgsantos.libraryapi.controllers.dto.UsuarioDTO;
import io.github.taryckgsantos.libraryapi.model.Usuario;
import io.github.taryckgsantos.libraryapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    public Usuario insert(Usuario usuario){
        if (usuarioRepository.findByLogin(usuario.getLogin()) != null) {
            throw new RuntimeException("Login já existe");
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public Usuario obterPorLogin(String login){
         return usuarioRepository.findByLogin(login);
    }

    public Usuario fromDTO(UsuarioDTO usuarioDTO){
        return new Usuario(
                usuarioDTO.getLogin(),
                usuarioDTO.getSenha(),
                usuarioDTO.getRoles()
        );
    }
}
