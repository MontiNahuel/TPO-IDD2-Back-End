package org.example.idi2.modelo.entidad;


public class MetodoDePago {
    private String tipoDePago;
    private int nroTarjeta;
    private String nombreAsociado;

    public MetodoDePago() {
    }

    public MetodoDePago(String tipoDePago, int nroTarjeta, String nombreAsociado) {
        this.tipoDePago = tipoDePago;
        this.nroTarjeta = nroTarjeta;
        this.nombreAsociado = nombreAsociado;
    }

    public String getTipoDePago() {
        return tipoDePago;
    }

    public void setTipoDePago(String tipoDePago) {
        this.tipoDePago = tipoDePago;
    }

    public int getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(int nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getNombreAsociado() {
        return nombreAsociado;
    }

    public void setNombreAsociado(String nombreAsociado) {
        this.nombreAsociado = nombreAsociado;
    }

    @Override
    public String toString() {
        return "MetodoDePago{" +
                "tipoDePago='" + tipoDePago + '\'' +
                ", nroTarjeta=" + nroTarjeta +
                ", nombreAsociado='" + nombreAsociado + '\'' +
                '}';
    }
}
