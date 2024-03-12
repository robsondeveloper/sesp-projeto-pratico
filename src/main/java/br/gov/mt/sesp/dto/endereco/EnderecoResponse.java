package br.gov.mt.sesp.dto.endereco;

import br.gov.mt.sesp.dto.pessoa.PessoaResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoResponse {

    private Long id;
    private String logradouro;
    private String bairro;
    private Integer numero;
    private String cidade;
    private String estado;
    private String cep;
    private PessoaResponse pessoa;
}
