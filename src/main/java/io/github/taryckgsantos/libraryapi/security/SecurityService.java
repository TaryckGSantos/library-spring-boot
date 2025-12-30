package io.github.taryckgsantos.libraryapi.security;

import io.github.taryckgsantos.libraryapi.model.Usuario;
import io.github.taryckgsantos.libraryapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    @Autowired
    private UsuarioService usuarioService;

    public Usuario obterUsuarioLogado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        assert userDetails != null;
        String login = userDetails.getUsername();
        return usuarioService.obterPorLogin(login);
    }
}
