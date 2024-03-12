package br.gov.mt.sesp.resource;

import br.gov.mt.sesp.dto.endereco.EnderecoResponse;
import br.gov.mt.sesp.service.EnderecoService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/v1/enderecos")
public class EnderecoResource {

    private final EnderecoService service;

    public EnderecoResource(EnderecoService service) {
        this.service = service;
    }

    @GET
    public List<EnderecoResponse> listar() {
        return service.listar();
    }
}
