package Modelo;

public class Usuario {
    // Atributos privados (Encapsulamiento total)
    private final String username;
    private final String password;
    private String nombre;

    /**
     * Constructor para inicializar un nuevo usuario.
     */
    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

    /**
     * Valida las credenciales.
     */
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
