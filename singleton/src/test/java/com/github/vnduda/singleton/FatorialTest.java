package com.github.vnduda.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FatorialTest {
	
	@Test
	void fatorialCorreto() {
		Fatorial fatorial = Fatorial.getInstance();
		assertEquals(120, fatorial.calcule(5));
	}
}