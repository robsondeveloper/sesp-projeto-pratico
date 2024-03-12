package br.gov.mt.sesp.dto.pessoa;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PessoaRequest {

    private String nome;
    private String rg;
    private String cpf;
    private LocalDate nascimento;
    private String telefone;
    private String mae;
    private String pai;
}
