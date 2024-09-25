package org.example.salaoapp.service;

import org.example.salaoapp.model.entity.Cliente;
import org.example.salaoapp.model.entity.Cartao;
import org.example.salaoapp.model.entity.Telefone;
import org.example.salaoapp.model.repository.ClienteRepository;
import org.example.salaoapp.model.repository.CartaoRepository;
import org.example.salaoapp.model.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    private void validarCliente(Cliente cliente) {
        if (cliente.getNome().length() < 5) {
            throw new IllegalArgumentException("O nome do cliente deve ter pelo menos 5 caracteres.");
        }

        if (cliente.getEmail().length() < 10) {
            throw new IllegalArgumentException("O email do cliente deve ter pelo menos 10 caracteres.");
        }

        for (Cartao cartao : cliente.getCartao()) {
            if (cartao.getNumero().length() < 12) {
                throw new IllegalArgumentException("O número do cartão deve ter pelo menos 12 dígitos.");
            }
        }

        for (Telefone telefone : cliente.getTelefones()) {
            if (telefone.getNumero().length() < 9) {
                throw new IllegalArgumentException("O número do telefone deve ter pelo menos 9 dígitos.");
            }
        }
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente createCliente(Cliente cliente) {
        validarCliente(cliente);
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente clienteDetails) {
        validarCliente(clienteDetails);
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(clienteDetails.getNome());
            cliente.setEmail(clienteDetails.getEmail());
            cliente.setCartao(clienteDetails.getCartao());
            cliente.setTelefones(clienteDetails.getTelefones());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new RuntimeException("Cliente não encontrado com id " + id));
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public List<Cartao> getCartoesByClienteId(Long clienteId) {
        return cartaoRepository.findByClienteId(clienteId);
    }

    public List<Telefone> getTelefonesByClienteId(Long clienteId) {
        return telefoneRepository.findByClienteId(clienteId);
    }
}
