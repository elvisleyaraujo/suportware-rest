package br.com.suportware.rest.controller;

import br.com.suportware.rest.model.TabelasExportacao;
import br.com.suportware.rest.service.ConcentradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/concentrador")
public class ConcentradorController {

    @Autowired
    private ConcentradorService concentradorService;

    @GetMapping(value="geracaoarquivopdv")
    public ResponseEntity getBook(){

        boolean resp = concentradorService.startGeracaoArquivo();

        return new ResponseEntity(resp, HttpStatus.OK);

    }

}
