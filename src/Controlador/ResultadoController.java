package Controlador;

import Modelo.Resultado;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultadoController {
    private final List<Resultado> historial = new ArrayList<>();

    public void registrarResultado(Resultado resultado) {
        historial.add(0, resultado); // Agregar al principio para historial reciente
    }

    /**
     * Obtiene el historial reciente (las últimas 5 rondas).
     */
    public List<Resultado> getHistorialReciente(int maxRondas) {
        int max = Math.min(maxRondas, historial.size());
        return historial.subList(0, max);
    }

    public int getHistorialSize() {
        return historial.size();
    }

}
