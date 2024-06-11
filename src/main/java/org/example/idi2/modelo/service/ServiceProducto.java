package org.example.idi2.modelo.service;

import org.example.idi2.modelo.entidad.Producto;
import org.example.idi2.modelo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProducto {

    @Autowired
    private ProductoRepository productoRepository;

    public void guardar(Producto p) {
        try {
            productoRepository.save(p);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }
}
