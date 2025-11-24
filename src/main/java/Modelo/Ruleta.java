package Modelo;


import java.util.Arrays;
import java.util.Random;

public class Ruleta {
    private static final int MIN_NUMERO = 0;
    private static final int MAX_NUMERO = 36;
    private static final int[] NUMEROS_ROJOS = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};

    private final Random rng = new Random();
    private int saldo;


    public Ruleta(int saldoInicial) {
        if (saldoInicial < 0){
            throw new IllegalArgumentException("Dinero insuficiente");}
        this.saldo = saldoInicial;
    }

    public Ruleta() {
        this(0);
    }


    public int getSaldo() {
        return saldo;
    }

    public void depositar(int monto) {
        if (monto > 0) {
            this.saldo += monto;
        } else {
            throw new IllegalArgumentException("El monto del depósito debe ser un valor positivo");
        }
    }

    public void aplicarGanancia(int ganancia) {
        this.saldo += ganancia;
    }


    public int girarRuleta() {
        return rng.nextInt(MAX_NUMERO - MIN_NUMERO + 1) + MIN_NUMERO;
    }

    public static boolean esRojoEstatico(int n) {
        if (n == 0) return false;
        return Arrays.stream(NUMEROS_ROJOS).anyMatch(i -> i == n);
    }
}
