package org.example.salaoapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Cliente {
    @Id
    private Long id;

    private String nome;

    private String email;

    @OneToMany
    @JoinColumn(name = "cliente_id")
    private List<Cartao> cartao;

    @OneToMany
    @JoinColumn(name = "telefone_id")
    private List<Telefone> telefones;
}
