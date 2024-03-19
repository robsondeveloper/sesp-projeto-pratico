package br.gov.mt.sesp.service;

import br.gov.mt.sesp.dto.endereco.EnderecoRequest;
import br.gov.mt.sesp.dto.endereco.EnderecoResponse;
import br.gov.mt.sesp.mapper.EnderecoMapper;
import br.gov.mt.sesp.model.Endereco;
import br.gov.mt.sesp.repository.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

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

    public EnderecoResponse porId(Long id) {
        return mapper.toResponse(buscar(id));
    }

    @Transactional
    public EnderecoResponse criar(EnderecoRequest request) {
        var endereco = mapper.toModel(request);
        repository.persist(endereco);
        return mapper.toResponse(endereco);
    }

    @Transactional
    public void atualizar(Long id, EnderecoRequest request) {
        var endereco = buscar(id);
        mapper.update(request, endereco);
        repository.persist(endereco);
    }

    @Transactional
    public void deletar(Long id) {
        var endereco = buscar(id);
        repository.delete(endereco);
    }

    public List<EnderecoResponse> pesquisarPorCidadeEstado(String cidade, String estado) {
        return repository.find("cidade = ?1 and estado = ?2", cidade, estado)
                .stream().map(mapper::toResponse).toList();
    }

    private Endereco buscar(Long id) {
        return repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("endereço não encontrado"));
    }
}
