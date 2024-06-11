package org.example.idi2.modelo.entidad;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "pedidos")
public class Pedido {
    @Id
    private String pedidoId;
    private String usernamePedido;
    private List<ProductoParaPedido> productos;
    private boolean pago;

    public Pedido() {
    }

    public Pedido(String username) {
        this.usernamePedido = username;
        this.productos = new ArrayList<>();
        this.pago = false;
    }

    public Pedido(String pedidoId, String usernamePedido, List<ProductoParaPedido> productos, boolean pago) {
        this.pedidoId = pedidoId;
        this.usernamePedido = usernamePedido;
        this.productos = productos;
        this.pago = pago;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getUsernamePedido() {
        return usernamePedido;
    }

    public void setUsernamePedido(String usernamePedido) {
        this.usernamePedido = usernamePedido;
    }

    public List<ProductoParaPedido> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoParaPedido> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "pedidoId='" + pedidoId + '\'' +
                ", usernamePedido='" + usernamePedido + '\'' +
                ", productos=" + productos +
                ", pago=" + pago +
                '}';
    }

    public double obtenerTotal() {
        double total= 0;
        for (ProductoParaPedido p : productos) {
            total = total + p.getPrecio() * p.getCantProducto();
        }
        return total;
    }

}
