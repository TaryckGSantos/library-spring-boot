package io.github.taryckgsantos.libraryapi.security;

import io.github.taryckgsantos.libraryapi.model.Usuario;
import io.github.taryckgsantos.libraryapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String senhaDigitada = authentication.getCredentials().toString();

        Usuario usuarioEncontrado = usuarioService.obterPorLogin(login);

        if(usuarioEncontrado == null){
            throw erroUsuarioOuSenhaNaoEncontrado();
        }

        String senhaCriptografada = usuarioEncontrado.getSenha();

        boolean senhasBatem = passwordEncoder.matches(senhaDigitada, senhaCriptografada);

        if(senhasBatem){
            return new CustomAuthentication(usuarioEncontrado);
        }

        throw erroUsuarioOuSenhaNaoEncontrado();
    }

    private static UsernameNotFoundException erroUsuarioOuSenhaNaoEncontrado() {
        return new UsernameNotFoundException("Usuário e/ou senha incorretos!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
