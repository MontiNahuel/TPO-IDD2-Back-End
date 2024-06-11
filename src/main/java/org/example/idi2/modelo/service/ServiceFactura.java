package org.example.idi2.modelo.service;

import org.example.idi2.modelo.entidad.Factura;
import org.example.idi2.modelo.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceFactura {

    @Autowired
    FacturaRepository facturaRepository;

    public List<Factura> obtenerFacturasPorUsuario(String username) {
        return facturaRepository.findByUsername(username);
    }
    public void guardarFactura(Factura factura) {
        facturaRepository.save(factura);
    }
}
