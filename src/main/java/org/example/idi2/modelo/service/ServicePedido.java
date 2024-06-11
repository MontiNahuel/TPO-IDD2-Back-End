package org.example.idi2.modelo.service;

import org.example.idi2.modelo.entidad.Pedido;
import org.example.idi2.modelo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePedido {
    @Autowired
    PedidoRepository pedidoRepository;
    public void guardarPedido(Pedido p) {
        try {
            pedidoRepository.save(p);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Pedido> obtenerPedidosPorUsername(String username) {
        return pedidoRepository.findByUsernamePedido(username);
    }

    public void guardarVariosPedidos(List<Pedido> pedidos) {
        for (Pedido p : pedidos) {
            try {
                pedidoRepository.save(p);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void eliminarPedidos(List<Pedido> pedidos) {
        for (Pedido p : pedidos) {
            try {
                pedidoRepository.delete(p);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
