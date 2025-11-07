package Modelo;

import Modelo.ApuestaBase;

public class ApuestaImpar extends ApuestaBase {
    public ApuestaImpar(int monto) {
        super("IMPAR", monto);
    }
    @Override
    public boolean acierta(int numeroGanador) {
        // Comprueba si la paridad es IMPAR
        return ApuestaBase.getParidad(numeroGanador).equals("IMPAR");
    }
}



