package org.example.idi2.modelo.entidad;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class ParProductoCantidad {
    @Id
    @GeneratedValue
    private String id;
    @TargetNode
    private Producto producto;
    private int cantidad;

    public ParProductoCantidad(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ParProductoCantidad{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                '}';
    }
}
