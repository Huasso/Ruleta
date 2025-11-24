package Modelo;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EstadisticasTest {

    @Test
    void CalculoCorrectoDeMetricasTest() {
        Resultado r1 = new Resultado(1, new ApuestaRojo(100), true, 1100);

        Resultado r2 = new Resultado(3, new ApuestaRojo(100), true, 1200);

        Resultado r3 = new Resultado(5, new ApuestaNegro(100), false, 1100);

        List<Resultado> historial = Arrays.asList(r1, r2, r3);

        Estadisticas stats = new Estadisticas(historial);

        // Validaciones
        assertEquals(3, stats.getTotalJugadas(), "Total jugadas incorrecto");
        assertEquals(2, stats.getVictorias(), "Total victorias incorrecto");
        assertEquals(2, stats.getRachaMaxima(), "Racha máxima incorrecta");
        assertEquals("ROJO", stats.getTipoMasJugado(), "Tipo más jugado incorrecto");
        assertEquals(66.6, stats.getPorcentajeVictorias(), 0.1, "Porcentaje incorrecto");
    }
}