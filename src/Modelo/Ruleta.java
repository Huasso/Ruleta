package Modelo;

import java.util.Arrays;
import java.util.Random;

public class Ruleta {
    private static final int MIN_NUMERO = 0;
    private static final int MAX_NUMERO = 36;
    private static final int[] NUMEROS_ROJOS = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};

    private final Random rng = new Random();
    private int saldo; // Atributo privado

    // Constructores (manteniendo la flexibilidad)
    public Ruleta(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public Ruleta() {
        this(0);
    }

    // Getters y Métodos de Negocio
    public int getSaldo() {
        return saldo;
    }

    /**
     * Métdo de negocio para depositar saldo de forma segura y validada.
     */
    public void depositar(int monto) {
        if (monto > 0) {
            this.saldo += monto;
        } else {
            throw new IllegalArgumentException("El monto del depósito debe ser un valor positivo.");
        }
    }

    /**
     * Aplica la ganancia/pérdida al saldo. Es privado, solo llamado internamente.
     */
    private void aplicarGanancia(int ganancia) {
        this.saldo += ganancia;
    }

    // Lógica del juego (Métodos Públicos)

    /**
     * Simula el giro de la ruleta.
     */
    public int girarRuleta() {
        return rng.nextInt(MAX_NUMERO - MIN_NUMERO + 1) + MIN_NUMERO;
    }

    /**
     * Evalúa la apuesta y calcula la ganancia. Actualiza el saldo internamente.
     * @return La ganancia o pérdida generada (positivo = ganancia, negativo = pérdida).
     */
    public int evaluarRonda(int numeroGanador, TipoApuesta apuesta, int monto) {
        boolean acierto = evaluarAcierto(numeroGanador, apuesta);
        int ganancia = acierto ? monto : -monto;
        aplicarGanancia(ganancia);
        return ganancia;
    }

    /**
     * Determina si la apuesta fue acertada.
     */
    public boolean evaluarAcierto(int numero, TipoApuesta apuesta) {
        if (numero == 0) return false;

        switch (apuesta) {
            case ROJO:
                return esRojoEstatico(numero);
            case NEGRO:
                return !esRojoEstatico(numero);
            case PAR:
                return numero % 2 == 0;
            case IMPAR:
                return numero % 2 != 0;
            default:
                return false;
        }
    }

    /**
     * Métdo estático para verificar si un número es rojo (útil para Resultado.java).
     */
    public static boolean esRojoEstatico(int n) {
        if (n == 0) return false;
        // La búsqueda por stream no es lo más eficiente, pero es más legible en Java moderno
        return Arrays.stream(NUMEROS_ROJOS).anyMatch(i -> i == n);
    }
}
