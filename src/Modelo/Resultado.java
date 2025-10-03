package Modelo;

/**
 * Representa el resultado de una ronda de ruleta.
 */
public class Resultado {
    private final int numeroGanador;
    private final TipoApuesta apuestaRealizada;
    private final int monto;
    private final boolean acierto;
    private final int saldoFinal;

    public Resultado(int numeroGanador, TipoApuesta apuestaRealizada, int monto, boolean acierto, int saldoFinal) {
        this.numeroGanador = numeroGanador;
        this.apuestaRealizada = apuestaRealizada;
        this.monto = monto;
        this.acierto = acierto;
        this.saldoFinal = saldoFinal;
    }

    // Getters
    public int getNumeroGanador() { return numeroGanador; }
    public TipoApuesta getApuestaRealizada() { return apuestaRealizada; }
    public int getMonto() { return monto; }
    public boolean isAcierto() { return acierto; }
    public int getSaldoFinal() { return saldoFinal; }

    // Métdo utilitario para el historial
    @Override
    public String toString() {
        String colorRuleta = (numeroGanador == 0) ? "VERDE" : (Ruleta.esRojoEstatico(numeroGanador) ? "ROJO" : "NEGRO");
        String paridadRuleta = (numeroGanador == 0) ? "VERDE" : (numeroGanador % 2 == 0 ? "PAR" : "IMPAR");

        String tipoInfo = (apuestaRealizada == TipoApuesta.ROJO || apuestaRealizada == TipoApuesta.NEGRO) ? colorRuleta : paridadRuleta;

        return String.format("Número: %d (%s) | Apuesta: %s | Monto: $%d | %s | Saldo: $%d",
                numeroGanador, tipoInfo, apuestaRealizada, monto, (acierto ? "GANASTE" : "PERDISTE"), saldoFinal);
    }
}
