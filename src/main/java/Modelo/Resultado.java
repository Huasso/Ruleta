package Modelo;

/**
 * Representa el resultado de una ronda de ruleta.
 */
public class Resultado {
    private final int numeroGanador;
    private final ApuestaBase apuestaRealizada;
    private final int monto;
    private final boolean acierto;
    private final int saldoFinal;

    public Resultado(int numeroGanador, ApuestaBase apuesta, boolean acierto, int saldoFinal) {
        this.numeroGanador = numeroGanador;
        this.apuestaRealizada = apuesta;
        this.monto = apuesta.getMonto();
        this.acierto = acierto;
        this.saldoFinal = saldoFinal;
    }

    // Getters
    public int getNumeroGanador() { return numeroGanador; }
    public ApuestaBase getApuestaRealizada() { return apuestaRealizada; }
    public int getMonto() { return monto; }
    public boolean isAcierto() { return acierto; }
    public int getSaldoFinal() { return saldoFinal; }
    public ApuestaBase getTipoApuesta() { return apuestaRealizada; }

    @Override
    public String toString() {
        String colorRuleta = (numeroGanador == 0) ? "VERDE" : (Ruleta.esRojoEstatico(numeroGanador) ? "ROJO" : "NEGRO");
        String paridadRuleta = (numeroGanador == 0) ? "VERDE" : (numeroGanador % 2 == 0 ? "PAR" : "IMPAR");

        String etiquetaApuesta = apuestaRealizada.getEtiqueta();

        String tipoInfo = (etiquetaApuesta.equals("ROJO") || etiquetaApuesta.equals("NEGRO"))
                ? colorRuleta
                : paridadRuleta;

        return String.format("Número: %d (%s) | Apuesta: %s | Monto: $%d | %s | Saldo: $%d",
                numeroGanador, tipoInfo, etiquetaApuesta, monto, (acierto ? "GANASTE" : "PERDISTE"), saldoFinal);
    }

}
