package Launcher;

import Controlador.RuletaController;
import Controlador.SessionController;
import Vista.VentanaLogin;
import Modelo.RepositorioEnMemoria;
import Modelo.RepositorioArchivo;
import Modelo.IRepositorioResultados;

import javax.swing.SwingUtilities;

public class Launcher {

    public static void main(String[] args) {

        IRepositorioResultados repositorio;

        repositorio = new RepositorioEnMemoria();
        // 1. Inicializar Modelos y Controladores
        SessionController sessionController = new SessionController(repositorio);
        RuletaController ruletaController = new RuletaController(sessionController);

        // 2. Iniciar la Vista (VentanaLogin)
        SwingUtilities.invokeLater(() -> {

            VentanaLogin login = new VentanaLogin(sessionController, ruletaController);
            login.mostrarVentana();
        });
    }
}
