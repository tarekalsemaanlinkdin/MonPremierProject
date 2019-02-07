package com.example.produits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.produits.model.Produit;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

}
