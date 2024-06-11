package org.example.idi2.modelo.entidad;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "facturas")
public class Factura {
    @Id
    private String idFactura;
    private List<Pedido> idPedido; // A que pedido/s pertenece esa factura
    private String username; // A que usuario pertenece esa factura
    private MetodoDePago metodoDePagoUtilizado; // Que metodo de pago fue utilizado para pagar el pedido
    private Date fechaEmision;

    public Factura() {
    }

    public Factura(List<Pedido> idPedido,String username, MetodoDePago metodoDePagoUtilizado) {
        this.idPedido = idPedido;
        this.username = username;
        this.metodoDePagoUtilizado = metodoDePagoUtilizado;
        this.fechaEmision = new Date();
    }

    public Factura(String idFactura, List<Pedido> idPedido, String username, MetodoDePago metodoDePagoUtilizado, Date fechaEmision) {
        this.idFactura = idFactura;
        this.idPedido = idPedido;
        this.username = username;
        this.metodoDePagoUtilizado = metodoDePagoUtilizado;
        this.fechaEmision = fechaEmision;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public List<Pedido> getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(List<Pedido> idPedido) {
        this.idPedido = idPedido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MetodoDePago getMetodoDePagoUtilizado() {
        return metodoDePagoUtilizado;
    }

    public void setMetodoDePagoUtilizado(MetodoDePago metodoDePagoUtilizado) {
        this.metodoDePagoUtilizado = metodoDePagoUtilizado;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura='" + idFactura + '\'' +
                ", idPedido='" + idPedido + '\'' +
                ", username='" + username + '\'' +
                ", metodoDePagoUtilizado=" + metodoDePagoUtilizado +
                ", fechaEmision=" + fechaEmision +
                '}';
    }
}
