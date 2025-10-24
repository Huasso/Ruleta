package Modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estadisticas {
    // Atributos privados según el UML
    private final int totalJugadas;
    private final int victorias;
    private final double porcentajeVictorias;
    private final int rachaMaxima;
    private final TipoApuesta tipoMasJugado;

    /**
     * Constructor que recibe el historial de resultados y dispara el cálculo
     * de todas las métricas.
     * @param historial Lista de resultados del usuario actual.
     */
    public Estadisticas(List<Resultado> historial) {
        // Inicializar atributos a cero en caso de historial vacío
        if (historial == null || historial.isEmpty()) {
            this.totalJugadas = 0;
            this.victorias = 0;
            this.porcentajeVictorias = 0.0;
            this.rachaMaxima = 0;
            this.tipoMasJugado = null;
        } else {
            this.totalJugadas = historial.size();
            // Disparamos el cálculo y asignamos los resultados a los atributos finales
            Map<String, Object> metricas = calcularMetricas(historial);

            this.victorias = (int) metricas.get("victorias");
            this.rachaMaxima = (int) metricas.get("rachaMaxima");
            this.tipoMasJugado = (TipoApuesta) metricas.get("tipoMasJugado");

            // Cálculo directo del porcentaje
            this.porcentajeVictorias = (double) this.victorias / this.totalJugadas * 100.0;
        }
    }

    /**
     * Calcula todas las métricas requeridas iterando una sola vez sobre el historial.
     *
     * @param historial Lista de resultados.
     * @return Un mapa que contiene los valores calculados de victorias, racha máxima y tipo más jugado.
     */
    private Map<String, Object> calcularMetricas(List<Resultado> historial) {
        int victoriasCount = 0;
        int rachaActual = 0;
        int rachaMaximaCount = 0;

        // Mapa para contar la frecuencia de cada TipoApuesta
        Map<TipoApuesta, Integer> conteoTipos = new HashMap<>();

        for (Resultado resultado : historial) {
            // 1. Contar Victorias y Racha Máxima
            if (resultado.isAcierto()) {
                victoriasCount++;
                rachaActual++;
            } else {
                rachaActual = 0;
            }
            rachaMaximaCount = Math.max(rachaMaximaCount, rachaActual);

            // 2. Conteo de Tipo de Apuesta
            TipoApuesta tipo = resultado.getTipoApuesta();
            conteoTipos.put(tipo, conteoTipos.getOrDefault(tipo, 0) + 1);
        }

        // 3. Determinar el Tipo Más Jugado
        TipoApuesta tipoMasJugadoResult = null;
        int maxFrecuencia = 0;

        for (Map.Entry<TipoApuesta, Integer> entry : conteoTipos.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxFrecuencia = entry.getValue();
                tipoMasJugadoResult = entry.getKey();
            }
        }

        // Preparamos el mapa de resultados (para simplificar la asignación en el constructor)
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

    public TipoApuesta getTipoMasJugado() {
        return tipoMasJugado;
    }
}