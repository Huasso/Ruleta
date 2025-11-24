package Controlador;

import Modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RuletaControllerTest {

    private RuletaController ruletaController;
    private SessionController sessionController;

    @BeforeEach
    void setUp() {
        IRepositorioResultados repositorio = new RepositorioEnMemoria();
        sessionController = new SessionController(repositorio);
        sessionController.iniciarSesion("jugador", "1111");

        ruletaController = new RuletaController(sessionController);
    }

    @Test
    void JugarConApuestaNulaTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ruletaController.jugarRonda(null);
        });
        assertEquals("Apuesta requerida", exception.getMessage());
    }

    @Test
    void JugarMontoMayorASaldoTest() {
        int saldoActual = ruletaController.getSaldo();
        ApuestaBase apuestaExcesiva = new ApuestaRojo(saldoActual + 100);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ruletaController.jugarRonda(apuestaExcesiva);
        });
        assertEquals("Saldo insuficiente", exception.getMessage());
    }
}