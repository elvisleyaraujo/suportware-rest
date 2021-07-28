package br.com.suportware.rest.controller;

import br.com.suportware.rest.service.ConcentradorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/concentrador")
public class ConcentradorController {

    @Autowired
    private ConcentradorService concentradorService;

    @GetMapping(value="geracaoarquivopdv")
    public ResponseEntity geracaoArquivoPDV(){

        boolean resp = concentradorService.startGeracaoArquivo();

        log.error("mostra pra nós");

        return new ResponseEntity(resp, HttpStatus.OK);
    }

}
