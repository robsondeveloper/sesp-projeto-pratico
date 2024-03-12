package br.gov.mt.sesp.resource;

import br.gov.mt.sesp.dto.endereco.EnderecoRequest;
import br.gov.mt.sesp.dto.endereco.EnderecoResponse;
import br.gov.mt.sesp.service.EnderecoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

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

    @GET
    @Path("/{id}")
    public EnderecoResponse porId(@PathParam("id") Long id) {
        return service.porId(id);
    }

    @POST
    public Response criar(EnderecoRequest request) {
        return Response.created(null)
                .entity(service.criar(request))
                .build();
    }

    @PUT
    @Path("/{id}")
    public void atualizar(@PathParam("id") Long id, EnderecoRequest request) {
        service.atualizar(id, request);
    }

    @DELETE
    @Path("/{id}")
    public void deletar(@PathParam("id") Long id) {
        service.deletar(id);
    }
}
