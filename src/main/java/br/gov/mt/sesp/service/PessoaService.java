package br.gov.mt.sesp.service;

import br.gov.mt.sesp.dto.pessoa.PessoaResponse;
import br.gov.mt.sesp.mapper.PessoaMapper;
import br.gov.mt.sesp.repository.PessoaRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PessoaService {

    private final PessoaRepository repository;
    private final PessoaMapper mapper;

    public PessoaService(PessoaRepository repository, PessoaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PessoaResponse> listar() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }
}
