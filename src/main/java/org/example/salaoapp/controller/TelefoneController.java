package org.example.salaoapp.controller;

import org.example.salaoapp.model.entity.Telefone;
import org.example.salaoapp.service.TelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/telefone")
public class TelefoneController {

    @Autowired
    private TelefoneService telefoneService;

    @GetMapping
    public List<Telefone> getAllTelefones() {
        return telefoneService.getAllTelefones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Telefone> getTelefoneById(@PathVariable Long id) {
        Optional<Telefone> telefone = telefoneService.getTelefoneById(id);
        return telefone.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Telefone> createTelefone(@RequestBody Telefone telefone) {
        Telefone novoTelefone = telefoneService.createTelefone(telefone);
        return ResponseEntity.ok(novoTelefone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Telefone> updateTelefone(@PathVariable Long id, @RequestBody Telefone telefoneDetails) {
        Telefone telefoneAtualizado = telefoneService.updateTelefone(id, telefoneDetails);
        return ResponseEntity.ok(telefoneAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelefone(@PathVariable Long id) {
        telefoneService.deleteTelefone(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{id}")
    public List<Telefone> getTelefonesByClienteId(@PathVariable Long id) {
        return telefoneService.getTelefonesByClienteId(id);
    }
}
