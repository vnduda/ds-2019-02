package com.github.vnduda.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Estudantes {
	
	private List<String> nomes;
	
	private List<String> sobrenomes;
	
	private List<String> matriculas;
	
	private List<String> cpf;
	
	private List<String> titulos;
	
	public static class Builder {
		private List<String> nomes = new ArrayList<>();
		private List<String> sobrenomes = new ArrayList<>();
		private List<String> matriculas = new ArrayList<>();
		private List<String> cpf = new ArrayList<>();
		private List<String> titulos = new ArrayList<>();
		
		public Builder(final String nome) {
			nomes.add(nome);
		}
		
		public Builder addNome(final String nome) {
			nomes.add(nome);
			return this;
		}
		
		public Builder addSobrenome(final String sobrenome) {
            sobrenomes.add(sobrenome);
            return this;
        }
		
		public Builder addMatricula(final String matricula) {
			matriculas.add(matricula);
			return this;
		}
		
		public Builder addCpf(final String CPF) {
			cpf.add(CPF);
			return this;
		}
		
		public Builder addTitulo(final String titulo) {
            titulos.add(titulo);
            return this;
        }
		
		public Estudantes build() {
			return new Estudantes(this);
		}
	}
	
	private Estudantes(Builder builder) {
		nomes = Collections.unmodifiableList(builder.nomes);
        sobrenomes = Collections.unmodifiableList(builder.sobrenomes);
        matriculas = Collections.unmodifiableList(builder.matriculas);
        cpf = Collections.unmodifiableList(builder.cpf);
        titulos = Collections.unmodifiableList(builder.titulos);
	}
	
	public List<String> getNomes() {
		return nomes;
	}
	
	public List<String> getSobrenomes() {
        return sobrenomes;
    }
	
	public List<String> getMatriculas() {
		return matriculas;
	}
	
	public List<String> getCpf() {
		return cpf;
	}
	
	public List<String> getTitulos() {
        return titulos;
    }
	
	@Override
    public String toString() {
        final String parcial = String.join(" ",
                une(titulos), une(nomes), une(sobrenomes), une(matriculas), une(cpf));
 
        return parcial.trim().replaceAll(" +", " ");
    }

    
    private String une(List<String> lista) {
        return String.join(" ", lista);
    }
	
}