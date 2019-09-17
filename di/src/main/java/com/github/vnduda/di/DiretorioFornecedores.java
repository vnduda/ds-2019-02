package com.github.vnduda.di;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import com.github.vnduda.di.Leite;

public class DiretorioFornecedores {

    private Set<Supplier<Leite>> fornecedores = new HashSet<>();

    public List<String> fornecedores() {
        List<String> nomes = new ArrayList<>();
        
        for(Supplier<Leite> fornecedor : fornecedores) {
            Leite leite = fornecedor.get();
            nomes.add(leite.getFornecedor());
        }

        return nomes;
    }

    public void adiciona(Supplier<Leite> fornecedor) {
        fornecedores.add(fornecedor);
    }
}