package com.github.vnduda.singleton;

import java.util.stream.IntStream;

public final class Fatorial {
	
	/**
	 * Referência para a única instância da classe
	 */
	private static final Fatorial INSTANCIA = criaInstancia();
	
	public static int calcule(int valor) {
		return IntStream.rangeClosed(2, valor).reduce(1, (x, y) -> x * y);
	}
	
	private static Fatorial criaInstancia() {
		return new Fatorial();
	}
	
	public static Fatorial getInstance() {
		return INSTANCIA;
	}
	
	
}
