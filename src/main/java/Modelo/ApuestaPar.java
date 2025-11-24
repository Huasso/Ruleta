package Modelo;

import Modelo.ApuestaBase;

public class ApuestaPar extends ApuestaBase {
    public ApuestaPar(int monto) {
        super("PAR", monto);
    }

    @Override
    public boolean acierta(int numeroGanador) {
        // Comprueba si la paridad es PAR (y no VERDE/0)
        return ApuestaBase.getParidad(numeroGanador).equals("PAR");
    }
}