package Controlador;

import Modelo.*;

import java.util.List;

public class RuletaController {
    private final SessionController sessionController;

    public RuletaController(SessionController sessionController) {
        // Inicia el motor de juego con el saldo por defecto (1000)
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

    private IRepositorioResultados getRepositorio() {
        return sessionController.getUsuarioActual().getRepositorioResultados();
    }

    public Resultado jugarRonda(ApuestaBase apuesta) {
        if (apuesta == null) {
            throw new IllegalArgumentException("Apuesta requerida");
        }

            Ruleta motorRuleta = getMotorRuletaActual();
            int monto = apuesta.getMonto();

            if (monto > motorRuleta.getSaldo()) {
                throw new IllegalArgumentException("Saldo insuficiente.");
            }

            int numeroGanador = motorRuleta.girarRuleta();
            boolean acierto = apuesta.acierta(numeroGanador);
            int cambioSaldo = acierto ? monto : -monto;

            motorRuleta.aplicarGanancia(cambioSaldo);

            Resultado resultado = new Resultado(numeroGanador, apuesta, acierto, motorRuleta.getSaldo());

            getRepositorio().guardarResultado(resultado);

            return resultado;
    }

    public List<Resultado> getHistorialReciente(int maxRondas) {
        List<Resultado> historialCompleto = getRepositorio().obtenerTodos();
        int max = Math.min(maxRondas, historialCompleto.size());
        return historialCompleto.subList(0, max);
    }

    public List<Resultado> getHistorialCompleto() {
        return getRepositorio().obtenerTodos();
    }


    public Estadisticas calcularEstadisticasUsuario() {
        List<Resultado> historial = getRepositorio().obtenerTodos();

        return new Estadisticas(historial);
    }
}