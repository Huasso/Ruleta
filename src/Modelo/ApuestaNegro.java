package Modelo;

import Modelo.ApuestaBase;

public class ApuestaNegro extends ApuestaBase {
    public ApuestaNegro(int monto) {
        super("NEGRO", monto);
    }

    @Override
    public boolean acierta(int numeroGanador) {
        return ApuestaBase.getColor(numeroGanador).equals("NEGRO");
    }
}