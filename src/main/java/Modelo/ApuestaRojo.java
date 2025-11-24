package Modelo;

public class ApuestaRojo extends ApuestaBase {
    public ApuestaRojo(int monto) {
        super("ROJO", monto);
    }

    @Override
    public boolean acierta(int numeroGanador) {
        // Usa la utilidad de la superclase para comprobar el color del número ganador
        return ApuestaBase.getColor(numeroGanador).equals("ROJO");
    }
}





