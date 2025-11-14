package Modelo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEnMemoria implements IRepositorioResultados {

    private final List<Resultado> historialEnMemoria = new ArrayList<>();

    @Override
    public void guardarResultado(Resultado resultado) {
        historialEnMemoria.add(0, resultado);
    }

    @Override
    public List<Resultado> obtenerTodos() {
        return historialEnMemoria;
    }

    @Override
    public void cargarDatos() {
        System.out.println("Repositorio en memoria: Listo");
    }

    @Override
    public void guardarDatos() {
        System.out.println("Repositorio en memoria: Cierre (datos volatiles).");
    }
}