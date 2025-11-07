package Launcher;

import Controlador.RuletaController;
import Controlador.SessionController;
import Vista.VentanaLogin;

public class Launcher {

    public static void main(String[] args) {
        // 1. Inicializar Modelos y Controladores
        SessionController sessionController = new SessionController();
        RuletaController ruletaController = new RuletaController(sessionController);

        // 2. Iniciar la Vista (VentanaLogin)

            VentanaLogin login = new VentanaLogin(sessionController, ruletaController);
            login.mostrarVentana();

    }
}
