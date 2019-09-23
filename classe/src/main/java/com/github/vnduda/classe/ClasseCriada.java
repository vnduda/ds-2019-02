package com.github.vnduda.classe;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ClasseCriada extends FileInputStream implements AutoCloseable {
    
    public ClasseCriada(String caminho) throws FileNotFoundException {
        super(caminho);
    }

    @Override
    public void close() {
        throw new RuntimeException("método close chamado");
    }
    
    
}