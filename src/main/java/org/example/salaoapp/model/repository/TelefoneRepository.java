package org.example.salaoapp.model.repository;

import org.example.salaoapp.model.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
    List<Telefone> findByClienteId(Long clienteId);
}
