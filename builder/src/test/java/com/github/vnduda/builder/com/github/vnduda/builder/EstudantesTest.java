package com.github.vnduda.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EstudantesTest {
	
	@Test
	   public void builderCasoTrivial() {
	       Estudantes.Builder builder = new Estudantes.Builder("Amanda")
	               .addSobrenome("Rocha");
	       assertEquals("Amanda Rocha", builder.build().toString());
	   }

	@Test
	   public void verificaBuilder() {
	       Estudantes.Builder builder = new Estudantes.Builder("Amanda");
	       Estudantes nome = builder
	               .addSobrenome("Rocha")
	               .addMatricula("20190906")
	               .addCpf("70038391152")
	               .addTitulo("Aluna")
	               .build();
	       
	       final String titulos = "Aluna";
	       final String completo = "Amanda Rocha 20190906 70038391152";
	       final String full = String.join(" ", titulos, completo);
	       assertEquals(full, nome.toString());
	   }
}