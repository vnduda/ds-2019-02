package com.github.vnduda.singleton;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

class FatorialTest {
	
	@Test
	void fatorialCorreto() {
		Fatorial fatorial = Fatorial.getInstance();
		assertEquals(120, fatorial.calcule(5));
	}
}