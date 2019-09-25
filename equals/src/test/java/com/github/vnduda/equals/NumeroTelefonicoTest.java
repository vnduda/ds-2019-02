package com.github.vnduda.equals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class NumeroTelefonicoTest {

    @Test
    void verificaNumero() {
        NumeroTelefonico numero = new NumeroTelefonico("+55", "62", "123456789");
        Set<NumeroTelefonico> c = new HashSet<>();
        c.add(numero);
        NumeroTelefonico numeroBusca = new NumeroTelefonico("+55", "62", "123456789");
        assertTrue(c.contains(numeroBusca));
    }
}