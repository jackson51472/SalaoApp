package org.example.salaoapp.service;

import org.example.salaoapp.model.entity.Cartao;
import org.example.salaoapp.model.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    private void validarCartao(Cartao cartao) {
        if (cartao.getNumero().length() < 12) {
            throw new IllegalArgumentException("O número do cartão deve ter pelo menos 12 dígitos.");
        }
    }

    public List<Cartao> getAllCartoes() {
        return cartaoRepository.findAll();
    }


    public Optional<Cartao> getCartaoById(Long id) {
        return cartaoRepository.findById(id);
    }

    public Cartao createCartao(Cartao cartao) {
        validarCartao(cartao);
        return cartaoRepository.save(cartao);
    }

    public Cartao updateCartao(Long id, Cartao cartaoDetails) {
        validarCartao(cartaoDetails);
        return cartaoRepository.findById(id).map(cartao -> {
            cartao.setNumero(cartaoDetails.getNumero());
            return cartaoRepository.save(cartao);
        }).orElseThrow(() -> new RuntimeException("Cartão não encontrado com id " + id));
    }


    public void deleteCartao(Long id) {
        cartaoRepository.deleteById(id);
    }

    public List<Cartao> getCartoesByClienteId(Long clienteId) {
        return cartaoRepository.findByClienteId(clienteId);
    }
}
