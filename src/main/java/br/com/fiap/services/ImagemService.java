package br.com.fiap.services;

import br.com.fiap.domain.Imagem;
import org.springframework.web.multipart.MultipartFile;

public interface ImagemService {

    public String uploadImage(MultipartFile imageData);
}
