package br.gov.mt.sesp.service;

import br.gov.mt.sesp.dto.pessoa.PessoaRequest;
import br.gov.mt.sesp.dto.pessoa.PessoaResponse;
import br.gov.mt.sesp.mapper.PessoaMapper;
import br.gov.mt.sesp.model.Pessoa;
import br.gov.mt.sesp.repository.PessoaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

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

    public PessoaResponse porId(Long id) {
        return mapper.toResponse(buscar(id));
    }

    @Transactional
    public PessoaResponse criar(PessoaRequest request) {
        var pessoa = mapper.toModel(request);
        repository.persist(pessoa);
        return mapper.toResponse(pessoa);
    }

    @Transactional
    public void atualizar(Long id, PessoaRequest request) {
        var pessoa = buscar(id);
        mapper.update(request, pessoa);
        repository.persist(pessoa);
    }

    @Transactional
    public void deletar(Long id) {
        var pessoa = buscar(id);
        repository.delete(pessoa);
    }

    public List<PessoaResponse> pesquisarPorNome(String nome) {
        var parametroNome = "%".concat(nome.toUpperCase()).concat("%");
        return repository.find("upper(nome) like ?1", parametroNome)
                .stream().map(mapper::toResponse).toList();
    }

    public PessoaResponse pesquisarPorCpf(String cpf) {
        var pessoa = repository.find("cpf = ?1", cpf).singleResultOptional()
                .orElseThrow(() -> new NotFoundException("pessoa com esse CPF não encontrada"));
        return mapper.toResponse(pessoa);
    }

    private Pessoa buscar(Long id) {
        return repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("pessoa não encontrada"));
    }
}
