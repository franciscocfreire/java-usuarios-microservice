package br.com.fiap.controller;

import br.com.fiap.entity.Usuario;
import br.com.fiap.helper.Helper;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin
@RestController
@Api
public class UsuarioController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @ApiOperation("Lista usuarios")
    @GetMapping("/usuario")
    public List<Usuario> listaUsuarios() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //listarUsuarios(entityManager);
        Helper dao = new Helper(entityManager);
        List<Usuario> usuarios = dao.listarUsuarios();


        return usuarios;
    }

    @GetMapping("/usuario/{id}")
    public Usuario listaUsuarios(@PathVariable("id") int id) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //listarUsuarios(entityManager);
        Helper dao = new Helper(entityManager);
        Usuario usuario = dao.buscarUsuario(id);


        return usuario;
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

    @PutMapping(path = "/usuario", consumes = "application/json", produces = "application/json")
    public Usuario atualizarUsuario(@RequestBody Usuario usuario) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Helper dao = new Helper(entityManager);

        try {

            dao.updateUsuario(usuario);

        } catch (Exception e) {
            System.out.println("Erro na atualizacao do usuario: " + e.getMessage());
        }

        return usuario;
    }

    @DeleteMapping("/usuario/{id}")
    public void apagarUsuario(@PathVariable("id") int id) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Helper dao = new Helper(entityManager);

        try {

            dao.apagarUsuario(id);

        } catch (Exception e) {
            System.out.println("Erro na exclusao do usuario: " + e.getMessage());
        }
    }

    @PostMapping(value = "/fileServe",
            produces = {"application/json"},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Image> uploadFile(@RequestPart("file") MultipartFile imageData, @RequestPart("usuario") String usuarioJson) throws IOException {

        Usuario usuario = new Gson().fromJson(usuarioJson, Usuario.class);

        System.out.println(imageData.getContentType());


        br.com.fiap.services.ImagemService imagemService = new br.com.fiap.services.impl.ImagemServiceImpl();
        br.com.fiap.domain.Imagem imagem = new br.com.fiap.domain.Imagem();
        String fotoPath = imagemService.uploadImage(imageData);

        usuario.setFoto(fotoPath);
        inseriUsuario(usuario);
        return null;
    }


}