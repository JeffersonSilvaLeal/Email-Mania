package br.com.email.mania;

import org.junit.Test;

import br.com.email.mania.model.ObjetoEnviaEmail;


public class AppTest {

	@Test
	public void testeEmail() {

		ObjetoEnviaEmail objetoEnviaEmail = new ObjetoEnviaEmail("drankhar@gmail.com", "Jefferson Da Silva Leal",
				"Testando Email com JAVA", "Testando email com java");
		
		objetoEnviaEmail.enviarEmail();

	}
}
