package br.gov.mt.sesp.mapper;

import br.gov.mt.sesp.dto.pessoa.PessoaRequest;
import br.gov.mt.sesp.dto.pessoa.PessoaResponse;
import br.gov.mt.sesp.model.Pessoa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface PessoaMapper {

    PessoaResponse toResponse(Pessoa model);

    Pessoa toModel(PessoaRequest request);
}
