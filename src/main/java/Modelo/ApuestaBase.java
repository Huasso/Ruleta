package Modelo;

public abstract class ApuestaBase {
    private static final String color_rojo = "ROJO";
    private static final String color_negro = "NEGRO";
    private static final String color_verde = "VERDE";
    private static final String numero_par = "PAR";
    private static final String numero_impar = "IMPAR";

    protected String etiqueta;
    protected int monto;


    public ApuestaBase(String etiqueta, int monto) {
        this.etiqueta = etiqueta;
        this.monto = monto;
    }

    public abstract boolean acierta(int numeroGanador);

    // --- Getters ---
    public String getEtiqueta() {
        return etiqueta;
    }

    public int getMonto() {
        return monto;
    }


    public static String getColor(int numero) {
        if (numero == 0) return color_verde;

        int[] rojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        for (int r : rojos) {
            if (r == numero) return color_rojo;
        }
        return color_negro;
    }
    public static String getParidad(int numero) {
        if (numero == 0) return color_verde;
        return (numero % 2 == 0) ? numero_par : numero_impar;
    }
}
