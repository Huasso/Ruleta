package Launcher;

import Controlador.RuletaController;
import Controlador.SessionController;
import Controlador.ResultadoController;
import Vista.VentanaLogin;

import javax.swing.SwingUtilities;

public class Launcher {

    /**
     * Méodo principal: inicia el programa en el hilo de eventos de Swing.
     * Inicializa la capa de controladores.
     */
    public static void main(String[] args) {
        // 1. Inicializar Modelos y Controladores
        ResultadoController resultadoController = new ResultadoController();
        RuletaController ruletaController = new RuletaController(resultadoController);
        SessionController sessionController = new SessionController();

        // 2. Iniciar la Vista (VentanaLogin)

            VentanaLogin login = new VentanaLogin(sessionController, ruletaController, resultadoController);
            login.mostrarVentana();

    }
}
