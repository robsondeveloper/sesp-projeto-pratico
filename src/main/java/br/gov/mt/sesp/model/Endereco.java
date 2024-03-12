package br.gov.mt.sesp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;

    private String bairro;

    private Integer numero;

    private String cidade;

    private String estado;

    private String cep;

    @OneToOne(optional = false)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;
}
