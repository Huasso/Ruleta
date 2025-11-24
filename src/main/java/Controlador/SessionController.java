package Controlador;

import Modelo.IRepositorioResultados;
import Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class SessionController {
    // Lista de usuarios hardcodeados, simulando la base de datos
    private static final List<Usuario> USUARIOS = new ArrayList<>();
    private Usuario usuarioActual;

    private final IRepositorioResultados repositorioResultados;

    public SessionController(IRepositorioResultados repositorio) {
        this.repositorioResultados = repositorio;

        if (USUARIOS.isEmpty()) {
            USUARIOS.add(new Usuario("admin", "1234", "Don Donnie", this.repositorioResultados));
            USUARIOS.add(new Usuario("jugador", "1111", "Jugador Pro", this.repositorioResultados));
            USUARIOS.add(new Usuario("invitado", "0000", "Invitado del Casino", this.repositorioResultados));
        }
    }

    public boolean iniciarSesion(String u, String p) {
        Usuario usuarioAutenticado = validarCredenciales(u, p);
        if (usuarioAutenticado != null) {
            this.usuarioActual = usuarioAutenticado;
            return true;
        }
        return false;
    }

    private Usuario validarCredenciales(String u, String p) {
        for (Usuario user : USUARIOS) {
            if (user.validarCredenciales(u, p)) {
                return user;
            }
        }
        return null;
    }

    public void cerrarSesion() {
        this.usuarioActual = null;
    }

    public boolean haySesionActiva() {
        return usuarioActual != null;
    }

    public String getNombreUsuario() {
        return haySesionActiva() ? usuarioActual.getNombre() : "";
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setNombreUsuario(String nuevoNombre) {
        if (haySesionActiva()) {
            usuarioActual.setNombre(nuevoNombre);
        }
    }

    public void registrarUsuario(String u, String p, String n) {
        if (u == null || u.isBlank() || p == null || p.isBlank() || n == null || n.isBlank()) {
            throw new IllegalArgumentException("Todos los datos son requeridos.");
        }

        if (USUARIOS.stream().anyMatch(user -> user.getUsername().equals(u))) {
            throw new IllegalArgumentException("El usuario ya existe.");
        }

        USUARIOS.add(new Usuario(u, p, n, repositorioResultados));
    }
}
