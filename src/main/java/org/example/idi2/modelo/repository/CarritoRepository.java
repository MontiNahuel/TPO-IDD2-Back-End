package org.example.idi2.modelo.repository;

import org.example.idi2.modelo.entidad.Carrito;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends Neo4jRepository<Carrito, Long> {

    Carrito findCarritoByUsernameId(String id);

    @Query("MATCH (c:Carrito) WHERE id(c) = $carritoId " +
            "MATCH (p:Producto) WHERE id(p) = $productoId " +
            "MERGE (c)-[r:tiene]->(p) " +
            "ON CREATE SET r.cantidad = $cantidad " +
            "ON MATCH SET r.cantidad = r.cantidad + $cantidad")
    void agregarProductoACarrito(Long carritoId, Long productoId, int cantidad);

    @Query("MATCH (c:Carrito)-[r:tiene]->(p:Producto) WHERE id(c) = $carritoId AND id(p) = $productoId DELETE r")
    void eliminarProductoDeCarrito(Long carritoId, Long productoId);

    @Query("MATCH (c:Carrito)-[r:tiene]->(p:Producto) WHERE id(c) = $carritoId DELETE r")
    void eliminarTodosLosProductos(Long carritoId);

}
