package Controlador;

import Modelo.Ruleta;
import Modelo.Resultado;
import Modelo.TipoApuesta;

public class RuletaController {
    private final Ruleta motorRuleta;
    private final ResultadoController resultadoController;

    public RuletaController(ResultadoController resultadoController) {
        // Inicia el motor de juego con el saldo por defecto de la aplicación (1000)
        this.motorRuleta = new Ruleta(1000);
        this.resultadoController = resultadoController;
    }

    public int getSaldo() {
        return motorRuleta.getSaldo();
    }

    public void depositar(int monto) {
        motorRuleta.depositar(monto);
    }

    /**
     * Ejecuta una ronda completa del juego, actualiza el saldo y registra el resultado.
     * @return El objeto Resultado de la ronda.
     */
    public Resultado jugarRonda(TipoApuesta apuesta, int monto) {
        if (monto > motorRuleta.getSaldo()) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

        int numeroGanador = motorRuleta.girarRuleta();
        int ganancia = motorRuleta.evaluarRonda(numeroGanador, apuesta, monto);
        boolean acierto = ganancia > 0;

        // Crear el objeto resultado
        Resultado resultado = new Resultado(numeroGanador, apuesta, monto, acierto, motorRuleta.getSaldo());

        // Registrar en el historial
        resultadoController.registrarResultado(resultado);

        return resultado;
    }

    public ResultadoController getResultadoController() {
        return resultadoController;
    }
}