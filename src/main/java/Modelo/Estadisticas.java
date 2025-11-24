package Modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estadisticas {
    private final int totalJugadas;
    private final int victorias;
    private final double porcentajeVictorias;
    private final int rachaMaxima;
    private final String tipoMasJugado;

    public Estadisticas(List<Resultado> historial) {

        if (historial == null || historial.isEmpty()) {
            this.totalJugadas = 0;
            this.victorias = 0;
            this.porcentajeVictorias = 0.0;
            this.rachaMaxima = 0;
            this.tipoMasJugado = "N/A";
        } else {
            this.totalJugadas = historial.size();
            Map<String, Object> metricas = calcularMetricas(historial);

            this.victorias = (int) metricas.get("victorias");
            this.rachaMaxima = (int) metricas.get("rachaMaxima");
            this.tipoMasJugado = (String) metricas.get("tipoMasJugado");

            this.porcentajeVictorias = (double) this.victorias / this.totalJugadas * 100.0;
        }
    }

    private Map<String, Object> calcularMetricas(List<Resultado> historial) {
        int victoriasCount = 0;
        int rachaActual = 0;
        int rachaMaximaCount = 0;


        Map<String, Integer> conteoTipos = new HashMap<>();

        for (Resultado resultado : historial) {
            if (resultado.isAcierto()) {
                victoriasCount++;
                rachaActual++;
            } else {
                rachaActual = 0;
            }
            rachaMaximaCount = Math.max(rachaMaximaCount, rachaActual);

            String tipo = resultado.getApuestaRealizada().getEtiqueta();
            conteoTipos.put(tipo, conteoTipos.getOrDefault(tipo, 0) + 1);
        }

        String tipoMasJugadoResult = "N/A";
        int maxFrecuencia = 0;

        for (Map.Entry<String, Integer> entry : conteoTipos.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxFrecuencia = entry.getValue();
                tipoMasJugadoResult = entry.getKey();
            }
        }

        Map<String, Object> metricas = new HashMap<>();
        metricas.put("victorias", victoriasCount);
        metricas.put("rachaMaxima", rachaMaximaCount);
        metricas.put("tipoMasJugado", tipoMasJugadoResult);

        return metricas;
    }

    // --- Getters Públicos (según el UML) ---

    public int getTotalJugadas() {
        return totalJugadas;
    }

    public int getVictorias() {
        return victorias;
    }

    public double getPorcentajeVictorias() {
        return porcentajeVictorias;
    }

    public int getRachaMaxima() {
        return rachaMaxima;
    }

    public String getTipoMasJugado() {
        return tipoMasJugado;
    }
}