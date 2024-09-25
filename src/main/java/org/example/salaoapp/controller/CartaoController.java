package org.example.salaoapp.controller;

import org.example.salaoapp.model.entity.Cartao;
import org.example.salaoapp.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/cartao")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @GetMapping
    public List<Cartao> getAllCartoes() {
        return cartaoService.getAllCartoes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cartao> getCartaoById(@PathVariable Long id) {
        Optional<Cartao> cartao = cartaoService.getCartaoById(id);
        return cartao.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cartao> createCartao(@RequestBody Cartao cartao) {
        Cartao novoCartao = cartaoService.createCartao(cartao);
        return ResponseEntity.ok(novoCartao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cartao> updateCartao(@PathVariable Long id, @RequestBody Cartao cartaoDetails) {
        Cartao cartaoAtualizado = cartaoService.updateCartao(id, cartaoDetails);
        return ResponseEntity.ok(cartaoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartao(@PathVariable Long id) {
        cartaoService.deleteCartao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Cartao> getCartoesByClienteId(@PathVariable Long clienteId) {
        return cartaoService.getCartoesByClienteId(clienteId);
    }
}
