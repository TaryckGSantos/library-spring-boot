package io.github.taryckgsantos.libraryapi.repository;

import io.github.taryckgsantos.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarRegistro(){
        Autor autor = new Autor();
        autor.setNome("José Maria");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1990, 1, 31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }
}
