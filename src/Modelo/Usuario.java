package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;


public class Usuario {
    private final String username;
    private final String password;
    private String nombre;

    private final Ruleta motorRuleta;
    private final IRepositorioResultados repositorioResultados;

    public Usuario(String username, String password, String nombre, IRepositorioResultados repositorio) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;

        this.repositorioResultados = repositorio;
        this.motorRuleta = new Ruleta(1000);
    }

    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }


    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    public Ruleta getMotorRuleta() {
        return motorRuleta;
    }

    public IRepositorioResultados getRepositorioResultados() {
        return repositorioResultados;
    }

    
    public void setNombre(String nuevoNombre) {
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            this.nombre = nuevoNombre;
        } else {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
    }
}
