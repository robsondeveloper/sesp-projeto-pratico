package br.gov.mt.sesp.dto.pessoa;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PessoaPesquisaRequest {

    @QueryParam("nome")
    @DefaultValue("")
    private String nome;

    @QueryParam("cpf")
    @DefaultValue("")
    private String cpf;

    @QueryParam("nascimento")
    private LocalDate nascimento;
}
