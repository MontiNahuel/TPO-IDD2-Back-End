package org.example.idi2.modelo.repository;

import org.example.idi2.modelo.entidad.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PedidoRepository extends MongoRepository<Pedido, String> {
    List<Pedido> findByUsernamePedido(String username);
}
