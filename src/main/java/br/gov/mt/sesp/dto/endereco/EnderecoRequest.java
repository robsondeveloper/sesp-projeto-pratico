package br.gov.mt.sesp.dto.endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequest {

    private String logradouro;
    private String bairro;
    private Integer numero;
    private String cidade;
    private String estado;
    private String cep;
    private Long pessoaId;
}
