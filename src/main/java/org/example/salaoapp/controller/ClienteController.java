package org.example.salaoapp.controller;

import org.example.salaoapp.model.entity.Cliente;
import org.example.salaoapp.model.entity.Cartao;
import org.example.salaoapp.model.entity.Telefone;
import org.example.salaoapp.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
        try {
            Cliente novoCliente = clienteService.createCliente(cliente);
            return ResponseEntity.ok(novoCliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {
        try {
            Cliente updatedCliente = clienteService.updateCliente(id, clienteDetails);
            return ResponseEntity.ok(updatedCliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/cartao")
    public ResponseEntity<List<Cartao>> getCartoesByClienteId(@PathVariable Long id) {
        List<Cartao> cartoes = clienteService.getCartoesByClienteId(id);
        return ResponseEntity.ok(cartoes);
    }

    @GetMapping("/{id}/telefone")
    public ResponseEntity<List<Telefone>> getTelefonesByClienteId(@PathVariable Long id) {
        List<Telefone> telefones = clienteService.getTelefonesByClienteId(id);
        return ResponseEntity.ok(telefones);
    }
}
