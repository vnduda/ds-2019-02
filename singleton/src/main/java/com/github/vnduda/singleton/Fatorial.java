package com.github.vnduda.singleton;

import java.util.stream.IntStream;

public final class Fatorial {
	
	/**
	 * Referência para a única instância da classe
	 */
	private static final Fatorial INSTANCIA = new Fatorial();

	public static int calcule(int valor) {
		// FIXME nenhuma verificação foi fornecida
		return IntStream.rangeClosed(2, valor).reduce(1, (x, y) -> x * y);
	}

	public static Fatorial getInstance() {
		return INSTANCIA;
	}

	/**
	 * Exigido para implementação do Singleton.
	 */
	private Fatorial() {
	}
}
