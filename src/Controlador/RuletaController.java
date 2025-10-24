package Controlador;

import Modelo.Estadisticas;
import Modelo.Ruleta;
import Modelo.Resultado;
import Modelo.TipoApuesta;

import java.util.List;

public class RuletaController {
    private final SessionController sessionController;

    public RuletaController(SessionController sessionController) {
        // Inicia el motor de juego con el saldo por defecto de la aplicación (1000)
        this.sessionController = sessionController;
    }

    private Ruleta getMotorRuletaActual() {
        return sessionController.getUsuarioActual().getMotorRuleta();
    }

    public int getSaldo() {
        return getMotorRuletaActual().getSaldo();
    }

    public void depositar(int monto) {
        getMotorRuletaActual().depositar(monto);
    }

    /**
     * Ejecuta una ronda completa del juego, actualiza el saldo y registra el resultado.
     * @return El objeto Resultado de la ronda.
     */
    public Resultado jugarRonda(TipoApuesta apuesta, int monto) {
        Ruleta motorRuleta = getMotorRuletaActual();//Ruleta actual del usuario

        if (monto > motorRuleta.getSaldo()) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

        int numeroGanador = motorRuleta.girarRuleta();
        int ganancia = motorRuleta.evaluarRonda(numeroGanador, apuesta, monto);
        boolean acierto = ganancia > 0;

        // Crear el objeto resultado
        Resultado resultado = new Resultado(numeroGanador, apuesta, monto, acierto, motorRuleta.getSaldo());

        // Registrar en el historial
        sessionController.getUsuarioActual().agregarResultado(resultado);

        return resultado;
    }

    public List<Resultado> getHistorialReciente(int maxRondas) {
        List<Resultado> historialCompleto = sessionController.getUsuarioActual().getHistorialResultados();
        int max = Math.min(maxRondas, historialCompleto.size());
        return historialCompleto.subList(0, max);
    }

    public List<Resultado> getHistorialCompleto() {
        return sessionController.getUsuarioActual().getHistorialResultados();
    }

    public Estadisticas calcularEstadisticasUsuario() {
        // 1. Obtiene el historial del usuario actual
        List<Resultado> historial = sessionController.getUsuarioActual().getHistorialResultados();

        return new Estadisticas(historial);
    }
}