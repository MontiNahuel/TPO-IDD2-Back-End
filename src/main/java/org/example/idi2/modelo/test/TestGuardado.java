package org.example.idi2.modelo.test;

import org.example.idi2.modelo.entidad.Carrito;
import org.example.idi2.modelo.entidad.Producto;
import org.example.idi2.modelo.service.ServiceCarrito;
import org.example.idi2.modelo.service.ServiceProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestGuardado {
    @Autowired
    private ServiceCarrito s1;
    @Autowired
    private ServiceProducto p2;
    public void guardarCarrito() {
        Carrito c = s1.obtenerCarrito("Beto");
        Carrito c1 = new Carrito(c.getId(), c.getUsernameId(), c.getObjetos());
        System.out.println(c1);
        Producto p1 = p2.obtenerPorId(0L);
        Producto p3 = p2.obtenerPorId(2L);
        c1.agregarElemento(p1, 6);
        s1.actualizar(c1.getId(), p1.getId(), -12);
        System.out.println(c1);


    }
}
