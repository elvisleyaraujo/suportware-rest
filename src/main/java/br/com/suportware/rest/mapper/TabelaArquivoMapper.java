package br.com.suportware.rest.mapper;

import br.com.suportware.rest.model.TabelaArquivo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TabelaArquivoMapper {

    List<TabelaArquivo> recuperarTabelaArquivo(@Param("idFilial") Long idFilial,
                                               @Param("nomeDatabase") String nomeDatabase,
                                               @Param("nomeTabela") String nomeTabela,
                                               @Param("nomeTabelaPesquisa") String nomeTabelaPesquisa);

}
