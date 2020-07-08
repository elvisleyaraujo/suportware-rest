package br.com.suportware.rest.service;

import br.com.suportware.rest.mapper.FilialMapper;
import br.com.suportware.rest.mapper.TabelaArquivoMapper;
import br.com.suportware.rest.mapper.TabelasExportacaoMapper;
import br.com.suportware.rest.model.Filial;
import br.com.suportware.rest.model.TabelaArquivo;
import br.com.suportware.rest.model.TabelasExportacao;
import br.com.suportware.rest.util.GerarArquivoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ConcentradorService {

    private final TabelasExportacaoMapper tabelasExportacaoMapper;
    private final FilialMapper filialMapper;
    private final TabelaArquivoMapper tabelaArquivoMapper;
    private final GerarArquivoUtil gerarArquivoUtil;
    private List<Filial> filialList;

    public ConcentradorService(TabelasExportacaoMapper tabelasExportacaoMapper,
                               FilialMapper filialMapper,
                               TabelaArquivoMapper tabelaArquivoMapper,
                               GerarArquivoUtil gerarArquivoUtil) {
        this.tabelasExportacaoMapper = tabelasExportacaoMapper;
        this.filialMapper = filialMapper;
        this.tabelaArquivoMapper = tabelaArquivoMapper;
        this.gerarArquivoUtil = gerarArquivoUtil;

        filialList = filialMapper.recuperarFiliais();
    }

    public boolean startGeracaoArquivo(){

        List<TabelasExportacao> tabelasExportacaoList =  tabelasExportacaoMapper.recuperarTabelasExportacao();

        for (TabelasExportacao tabelasExportacao : tabelasExportacaoList){
            //Gerar Arquivo
            gerarArquivo(tabelasExportacao);
        }

        return false;
    }

    private boolean gerarArquivo(TabelasExportacao tabelasExportacao){
        for (Filial filial : filialList){

            Long idFilial = 0L;

            if (tabelasExportacao.isPorLoja()) idFilial = filial.getId();

            List<TabelaArquivo> tabelaArquivoList =  tabelaArquivoMapper.recuperarTabelaArquivo(idFilial,
                    tabelasExportacao.getNomeDatabase(),
                    tabelasExportacao.getNomeTabela(),
                    tabelasExportacao.getNomeTabelaConsulta());

            gerarArquivoUtil.gerarArquivo(filial, tabelasExportacao, tabelaArquivoList);
        }

        return true;
    }
}
