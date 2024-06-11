package org.example.idi2.modelo.service;

import org.example.idi2.modelo.entidad.Cuenta;
import org.example.idi2.modelo.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ServiceCuenta {
    @Autowired
    private CuentaRepository cuentaRepository;

    public void addCuenta(Cuenta c) {
        cuentaRepository.save(c);
    }

    public List<Cuenta> listAllCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta getCuentaById(String id) {
        return cuentaRepository.findById(id).orElse(null);
    }

    public void deleteCuentaById(String id) {
        cuentaRepository.deleteById(id);
    }

    public Cuenta getCuentaByUser(String user, String password) {
        Cuenta c = null;
        c = cuentaRepository.findByUsername(user);
        if (Objects.isNull(c)) {
            return c;
        } else if (c.getPassword().equals(password)) {
            return c;
        } else {
            return null;
        }
    }

    public Cuenta getCuentaByUsername(String username) {
        return cuentaRepository.findByUsername(username);
    }
}
