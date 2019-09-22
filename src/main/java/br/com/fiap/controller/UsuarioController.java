package br.com.fiap.controller;

import br.com.fiap.entity.Usuario;
import br.com.fiap.helper.Helper;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UsuarioController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Usuario greeting(@RequestParam(value="name", defaultValue="World") String name) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //listarUsuarios(entityManager);
        Helper dao = new Helper(entityManager);
        List<Usuario> usuarios = dao.listarUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println("Matr�cula: " + usuario.getIdUsuario() + " Nome: "
                    + usuario.getNomeUsuario() + "  Endere�o: "
                    + usuario.getEnderecoUsuario() + " Telefone: "
                    + usuario.getTelefoneUsuario() + "\n");
        }
        Usuario usuario = usuarios.get(0);
        return usuario;
    }

    @RequestMapping("/usuario")
    public List<Usuario> listaUsuarios() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //listarUsuarios(entityManager);
        Helper dao = new Helper(entityManager);
        List<Usuario> usuarios = dao.listarUsuarios();


        return usuarios;
    }

    @PostMapping(path = "/usuario", consumes = "application/json", produces = "application/json")
    public Usuario inseriUsuario(@RequestBody Usuario usuario) {
        //code

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Helper dao = new Helper(entityManager);

        try {
            dao.salvarUsuario(usuario);
        } catch (Exception e) {
            System.out.println("Erro na inclus�o do usuario: " + e.getMessage());
        }















        return usuario;
    }
}