package br.com.suportware.rest.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TabelasExportacao {

    String nomeTabela;
    boolean exportar;
    boolean porLoja;
    String nomeDatabase;
    String nomeTabelaConsulta;

}
