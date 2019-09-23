package com.github.vnduda.classe;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DiretorioFornecedoresTest {

    @Test
    void verificaCloseChamado() {
        assertThrows(RuntimeException.class, () -> {
            try (ClasseCriada obj = new ClasseCriada("teste")) {
            }
        });
    }
}