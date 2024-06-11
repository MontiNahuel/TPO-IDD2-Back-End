package org.example.idi2.modelo.service;

import org.example.idi2.modelo.entidad.Carrito;
import org.example.idi2.modelo.entidad.ParProductoCantidad;
import org.example.idi2.modelo.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ServiceCarrito {

    @Autowired
    private CarritoRepository carritoRepository;

    public Carrito obtenerCarrito(String username) {
        Carrito carrito = carritoRepository.findCarritoByUsernameId(username);
        if (Objects.isNull(carrito)) {
            carrito = new Carrito(username);
        }
        return carrito;
    }

    public void guardarCarrito(Carrito c) {
        try {
            carritoRepository.save(c);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void borrar(Carrito c) {
        try {
            carritoRepository.delete(c);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void actualizar(Long carritoId, Long productoId, int cant) {
        carritoRepository.agregarProductoACarrito(carritoId, productoId, cant);
    }

    public void borrarProductoDelCarrito(Long carritoId, Long productoId) {
        carritoRepository.eliminarProductoDeCarrito(carritoId, productoId);
    }

    public void eliminarTodosLosProductosDelCarrito(Long carritoId) {
        carritoRepository.eliminarTodosLosProductos(carritoId);
    }
}
