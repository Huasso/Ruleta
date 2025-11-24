package Modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuletaTest {

    private Ruleta ruleta;

    @BeforeEach
    void setUp() {
         ruleta = new Ruleta(1000);
    }

    @Test
    void constructorTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{ new Ruleta(-100);
        });

        String expectedMessage = "Saldo inicial invalido";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void depositarTest(){
        int saldoAnterior = ruleta.getSaldo();
        int montoADepositar = 500;

        ruleta.depositar(montoADepositar);

        int saldoEsperado = saldoAnterior + montoADepositar;
        assertEquals(saldoEsperado, ruleta.getSaldo());//1500 de saldo
    }

    @Test
    void DepositoInvalidoTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            ruleta.depositar(-100);
        });
    }
}