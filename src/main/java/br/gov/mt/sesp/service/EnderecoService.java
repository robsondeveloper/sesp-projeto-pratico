package br.gov.mt.sesp.service;

import br.gov.mt.sesp.dto.endereco.EnderecoResponse;
import br.gov.mt.sesp.mapper.EnderecoMapper;
import br.gov.mt.sesp.repository.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EnderecoService {

    private final EnderecoRepository repository;
    private final EnderecoMapper mapper;

    public EnderecoService(EnderecoRepository repository, EnderecoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<EnderecoResponse> listar() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }
}
