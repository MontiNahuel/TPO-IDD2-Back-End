package org.example.idi2.controller;

import org.example.idi2.extra.Response;
import org.example.idi2.modelo.dto.CuentaDTO;
import org.example.idi2.modelo.entidad.Cuenta;
import org.example.idi2.modelo.service.ServiceCuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ServiceCuenta cuentas;

    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody CuentaDTO cuenta) {
        try {
            Cuenta c = cuentas.getCuentaByUser(cuenta.getUsername(), cuenta.getPassword());
            if (c == null) {
                return new ResponseEntity<>(
                        new Response("Credenciales incorrectas", null), HttpStatus.CONFLICT
                );
            } else {
                return new ResponseEntity<>(new Response("Sesion iniciada", null), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new Response("Error al iniciar sesion", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
