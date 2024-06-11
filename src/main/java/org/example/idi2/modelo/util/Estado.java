package org.example.idi2.modelo.util;

import org.example.idi2.modelo.entidad.Carrito;

public class Estado {
    private String username;
    private String email;
    private boolean sesionIniciada;
    private Carrito carrito;

    public Estado() {
        username = null;
        email = null;
        sesionIniciada = false;
        carrito = new Carrito();
    }

    public Estado(String username, String email, boolean sesionIniciada, Carrito c) {
        this.username = username;
        this.email = email;
        this.sesionIniciada = sesionIniciada;
        this.carrito = c;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSesionIniciada() {
        return sesionIniciada;
    }

    public void setSesionIniciada(boolean sesionIniciada) {
        this.sesionIniciada = sesionIniciada;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", sesionIniciada=" + sesionIniciada +
                ", carrito=" + carrito +
                '}';
    }

    public void cerrarSesion() {
        sesionIniciada = false;
        username = null;
        email = null;
    }
}
