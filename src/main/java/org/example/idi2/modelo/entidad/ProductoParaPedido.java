package org.example.idi2.modelo.entidad;

public class ProductoParaPedido {
    private Long idProducto;
    private String nombreProducto;
    private double precio;
    private int cantProducto;

    public ProductoParaPedido(Long idProducto, String nombreProducto, double precio, int cantProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.cantProducto = cantProducto;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantProducto() {
        return cantProducto;
    }

    public void setCantProducto(int cantProducto) {
        this.cantProducto = cantProducto;
    }

    @Override
    public String toString() {
        return "PedidoParaProducto{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", precio=" + precio +
                ", cantProducto=" + cantProducto +
                '}';
    }

}
