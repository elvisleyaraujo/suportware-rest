package br.com.suportware.rest.model;

import lombok.Data;

@Data
public class TabelaArquivo {

    private String linha;

    @Override
    public String toString(){
        return linha;
    }

}
