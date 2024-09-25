package org.example.salaoapp.service;

import org.example.salaoapp.model.entity.Telefone;
import org.example.salaoapp.model.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    private void validarTelefone(Telefone telefone) {
        if (telefone.getNumero().length() < 9) {
            throw new IllegalArgumentException("O número do telefone deve ter pelo menos 9 dígitos.");
        }
    }

    public List<Telefone> getAllTelefones() {
        return telefoneRepository.findAll();
    }

    public Optional<Telefone> getTelefoneById(Long id) {
        return telefoneRepository.findById(id);
    }

    public Telefone createTelefone(Telefone telefone) {
        validarTelefone(telefone);
        return telefoneRepository.save(telefone);
    }

    public Telefone updateTelefone(Long id, Telefone telefoneDetails) {
        validarTelefone(telefoneDetails);
        return telefoneRepository.findById(id).map(telefone -> {
            telefone.setNumero(telefoneDetails.getNumero());
            return telefoneRepository.save(telefone);
        }).orElseThrow(() -> new RuntimeException("Telefone não encontrado com id " + id));
    }

    public void deleteTelefone(Long id) {
        telefoneRepository.deleteById(id);
    }

    public List<Telefone> getTelefonesByClienteId(Long clienteId) {
        return telefoneRepository.findByClienteId(clienteId);
    }
}
