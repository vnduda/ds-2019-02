package com.github.vnduda.di;

import org.junit.jupiter.api.Test;

class DiretorioFornecedoresTest {

    @Test 
    void confereInstanciaDiretorioFornecedores() {
        DiretorioFornecedores dirFornecedores = new DiretorioFornecedores();

        dirFornecedores.adiciona(new CaldasNovas());

        assertEquals("Caldas Novas", dirFornecedores.fornecedores().get(0));
    } 
}