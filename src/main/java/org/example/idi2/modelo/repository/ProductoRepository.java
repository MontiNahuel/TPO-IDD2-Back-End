package org.example.idi2.modelo.repository;

import org.example.idi2.modelo.entidad.Producto;
import org.springframework.data.neo4j.repository.Neo4jRepository;


import java.util.List;
import java.util.Optional;


public interface ProductoRepository extends Neo4jRepository<Producto, Long> {

    List<Producto> findAll();
}
