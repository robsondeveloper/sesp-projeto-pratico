package br.gov.mt.sesp.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String rg;

    @Column
    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDate nascimento;

    @Column
    private String telefone;

    @Column(name = "nome_mae")
    private String mae;

    @Column(name = "nome_pai")
    private String pai;

    @Setter(AccessLevel.NONE)
    @Column(name = "data_cadastro")
    private LocalDate cadastradoEm;

    @PrePersist
    public void prePersist() {
        this.cadastradoEm = LocalDate.now();
    }
}
