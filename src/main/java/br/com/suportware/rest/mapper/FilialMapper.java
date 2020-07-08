package br.com.suportware.rest.mapper;

import br.com.suportware.rest.model.Filial;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FilialMapper {

    List<Filial> recuperarFiliais();

}
