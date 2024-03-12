package br.gov.mt.sesp.mapper;

import br.gov.mt.sesp.dto.endereco.EnderecoRequest;
import br.gov.mt.sesp.dto.endereco.EnderecoResponse;
import br.gov.mt.sesp.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "jakarta")
public interface EnderecoMapper {

    EnderecoResponse toResponse(Endereco model);

    @Mapping(target = "pessoa.id", source = "pessoaId")
    Endereco toModel(EnderecoRequest request);

    void update(EnderecoRequest request, @MappingTarget Endereco model);
}
