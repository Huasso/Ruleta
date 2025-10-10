package Modelo;

import java.util.ArrayList;
import java.util.List;


public class Usuario {
    // Atributos privados (Encapsulamiento total)
    private final String username;
    private final String password;
    private String nombre;

    //Asociacion 1 a  muchos de Resultado
    private List<Resultado> historialResultados;
    /*
     * Constructor para inicializar un nuevo usuario.
     */
    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;

        this.historialResultados = new ArrayList<>();
    }

    /**
     * Valida las credenciales.
     */
    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }

    public void agregarResultado(Resultado resultado) {
        if (resultado != null) {
            historialResultados.add(resultado);
        }
    }

    public void limpiarHistorial() {
        historialResultados.clear();
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }
    public List<Resultado> getHistorialResultados() {
        return historialResultados;
    }

    /**
     * Setter validado para el nombre del usuario.
     */
    public void setNombre(String nuevoNombre) {
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            this.nombre = nuevoNombre;
        } else {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
    }
}
