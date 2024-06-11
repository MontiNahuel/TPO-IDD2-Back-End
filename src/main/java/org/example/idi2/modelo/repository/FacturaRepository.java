package org.example.idi2.modelo.repository;

import org.example.idi2.modelo.entidad.Factura;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FacturaRepository extends MongoRepository<Factura, String> {
    List<Factura> findByUsername(String username);
}
