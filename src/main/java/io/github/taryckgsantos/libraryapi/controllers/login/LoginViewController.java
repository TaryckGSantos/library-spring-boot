package io.github.taryckgsantos.libraryapi.controllers.login;

import io.github.taryckgsantos.libraryapi.security.CustomAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String paginaLogin(){
        return "login";
    }

    @GetMapping("/")
    @ResponseBody
    public String homePage(Authentication authentication){

        if(authentication instanceof CustomAuthentication customAuthentication){
            System.out.println(customAuthentication.getUsuario());
        }

        return "Olá " + authentication.getName();

    }
}
