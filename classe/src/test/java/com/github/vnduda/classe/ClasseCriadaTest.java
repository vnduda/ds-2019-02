package com.github.vnduda.classe;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DiretorioFornecedoresTest {

    // TODO veja como o Josenilton implementou a melhor estratégia para este teste. Aquela abaixo é "frágil" a execução satisfatória dela "depende", o que não é adequado em um teste.
    // TODO veja link https://github.com/newtonjose/ds-2019-02/blob/master/auto-closeable/src/test/java/com/github/newtonjose/autocloseable/FileInputStreamAutoCloseableTest.java
    @Test
    void verificaCloseChamado() {
        assertThrows(RuntimeException.class, () -> {
            try (ClasseCriada obj = new ClasseCriada("teste")) {
            }
        });
    }
}
