package org.example.idi2.modelo.repository;

import org.example.idi2.modelo.entidad.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface CuentaRepository extends MongoRepository<Cuenta, String> {
    Cuenta findByUsername(String s);
}
