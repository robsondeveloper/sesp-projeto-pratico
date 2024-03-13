package br.gov.mt.sesp.resource;

import br.gov.mt.sesp.dto.pessoa.PessoaRequest;
import br.gov.mt.sesp.dto.pessoa.PessoaResponse;
import br.gov.mt.sesp.service.PessoaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

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

    @GET
    @Path("/{id}")
    public PessoaResponse porId(@PathParam("id") Long id) {
        return service.porId(id);
    }

    @POST
    public Response criar(PessoaRequest request) {
        return Response.created(null)
                .entity(service.criar(request))
                .build();
    }

    @PUT
    @Path("/{id}")
    public void atualizar(@PathParam("id") Long id, PessoaRequest request) {
        service.atualizar(id, request);
    }

    @DELETE
    @Path("/{id}")
    public void deletar(@PathParam("id") Long id) {
        service.deletar(id);
    }

    @GET
    @Path("/por-nome")
    public List<PessoaResponse> pesquisarPorNome(@QueryParam("nome") @DefaultValue("") String nome) {
        return service.pesquisarPorNome(nome);
    }

    @GET
    @Path("/por-cpf")
    public PessoaResponse pesquisarPorCpf(@QueryParam("cpf") @DefaultValue("") String cpf) {
        return service.pesquisarPorCpf(cpf);
    }
}
