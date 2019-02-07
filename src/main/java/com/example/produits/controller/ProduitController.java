package com.example.produits.controller;

import com.example.produits.exception.ResourceNotFoundException;
import com.example.produits.model.Produit;
import com.example.produits.repository.ProduitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class ProduitController {

    @Autowired
    ProduitRepository produitRepository;

    @GetMapping("/produits")
    public List<Produit> getAllNotes() {
        return produitRepository.findAll();
    }

    @PostMapping("/produits")
    public Produit createNote(@Valid @RequestBody Produit produit) {
        return produitRepository.save(produit);
    }

    @GetMapping("/produits/{id}")
    public Produit getNoteById(@PathVariable(value = "id") Long produitId) {
        return produitRepository.findById(produitId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", produitId));
    }

    @PutMapping("/produits/{id}")
    public Produit updateNote(@PathVariable(value = "id") Long produitId,
                                           @Valid @RequestBody Produit produitDetails) {

        Produit note = produitRepository.findById(produitId)
                .orElseThrow(() -> new ResourceNotFoundException("produit", "id", produitId));

        note.setName(produitDetails.getName());
        note.setPhoto(produitDetails.getPhoto());
        note.setPrice(produitDetails.getPrice());
        note.setQuantity(produitDetails.getQuantity());
        
        Produit updatedProduit = produitRepository.save(note);
        return updatedProduit;
    }

    @DeleteMapping("/produits/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long produitId) {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", produitId));

        produitRepository.delete(produit);

        return ResponseEntity.ok().build();
    }
}
