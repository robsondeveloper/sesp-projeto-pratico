package br.gov.mt.sesp.resource;

import br.gov.mt.sesp.dto.pessoa.PessoaResponse;
import br.gov.mt.sesp.service.PessoaService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/v1/pessoas")
public class PessoaResource {

    private final PessoaService service;

    public PessoaResource(PessoaService service) {
        this.service = service;
    }

    @GET
    public List<PessoaResponse> listar() {
        return service.listar();
    }
}
