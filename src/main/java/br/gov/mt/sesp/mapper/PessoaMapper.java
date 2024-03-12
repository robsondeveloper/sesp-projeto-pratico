package br.gov.mt.sesp.mapper;

import br.gov.mt.sesp.dto.pessoa.PessoaRequest;
import br.gov.mt.sesp.dto.pessoa.PessoaResponse;
import br.gov.mt.sesp.model.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "jakarta")
public interface PessoaMapper {

    PessoaResponse toResponse(Pessoa model);

    Pessoa toModel(PessoaRequest request);

    void update(PessoaRequest request, @MappingTarget Pessoa model);
}
