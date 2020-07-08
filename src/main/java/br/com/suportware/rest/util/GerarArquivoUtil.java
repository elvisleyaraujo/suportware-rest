package br.com.suportware.rest.util;

import br.com.suportware.rest.model.Filial;
import br.com.suportware.rest.model.TabelaArquivo;
import br.com.suportware.rest.model.TabelasExportacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class GerarArquivoUtil {

    private final String caminhoArquivo;

    public GerarArquivoUtil(@Value("${params.path.arquivo}") String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public boolean gerarArquivo(Filial filial, TabelasExportacao tabelasExportacao, List<TabelaArquivo> tabelaArquivoList) {
        if (!tabelaArquivoList.isEmpty()) {
            String pathArquivo = String.format("%s%s%s%s%s", caminhoArquivo,
                                                             "\\loja"+String.format("%02d",filial.getId()),
                                                             "\\full",
                                                             "\\" + tabelasExportacao.getNomeTabela(),
                                                             ".txt"
                                                         );

            File arquivo = new File(pathArquivo);
            if (!arquivo.exists()) {
                try (
                        FileWriter fw = new FileWriter(arquivo);
                        BufferedWriter bw = new BufferedWriter(fw);
                ) {
                    for (TabelaArquivo tabelaArquivo : tabelaArquivoList) {
                        bw.write(tabelaArquivo.toString());
                        bw.newLine();
                    }

                } catch (IOException e) {
                    log.error(e.getMessage());
                    return false;
                }

                //try {
                //    Files.delete(arquivo.toPath());
                //} catch (Exception e){
                //    log.error("ERRO AO DELETAR ARQUIVO FILIAL {} DATA {}", filial.getId(), dataMovimento);
                //}
            }

            return true;

        } else {
            return false;
        }

    }


}
