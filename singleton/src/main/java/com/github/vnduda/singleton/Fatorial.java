package com.github.vnduda.singleton;

public final class Fatorial {
	
	/**
	 * Referência para a única instância da classe
	 */
	private static final Fatorial INSTANCIA = criaInstancia();
	
	private int CalculoFatorial(int valor) {
		int fat = 0;

		for(int i = 2; i <= valor; i++){
			fat *= i;
		}
		return fat;
	}
	
	private static Fatorial criaInstancia() {
		return new Fatorial();
	}
	
	public static Fatorial getInstance() {
		return INSTANCIA;
	}
	
	
}
