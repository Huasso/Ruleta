package Controlador;

import Modelo.IRepositorioResultados;
import Modelo.RepositorioEnMemoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SessionControllerTest {

    private SessionController sessionController;

    @BeforeEach
    void setUp() {
        IRepositorioResultados repositorio = new RepositorioEnMemoria();
        sessionController = new SessionController(repositorio);
    }

    @Test
    void InicioConUsuarioNoRegistradoTest() {
        boolean resultado = sessionController.iniciarSesion("usuarioFantasma", "1234");
        assertFalse(resultado, "Deberia fallar el login con usuario no existente");
        assertFalse(sessionController.haySesionActiva());
    }

    @Test
    void RegistroUsernameNuloTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sessionController.registrarUsuario(null, "password", "Nombre");
        });
        assertTrue(exception.getMessage().contains("requeridos") || exception.getMessage().contains("nulo"));
    }
}