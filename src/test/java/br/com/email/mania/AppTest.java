package br.com.email.mania;

import org.junit.Test;

import br.com.email.mania.model.ObjetoEnviaEmail;

public class AppTest {
	
	@Test
	public void testeEmail() {
		
		StringBuilder builderTextoEmail = new StringBuilder();
		builderTextoEmail.append("Olá, tudo bem?<br/><br/>");
		builderTextoEmail.append("Voçê está recebendo esse email de javaMail, gosta de <b>StarWars</b>?<br/><br/>");
		builderTextoEmail.append("Click no Link abaixo,<br/><br/>");
		builderTextoEmail.append("<a target=\"_blank\" href=\"https://www.starwars.com\" style=\"color:#39ff14; padding: 14px 25px; text-align:center; text-decoration:none; display:inline-block; border-radius:30px; font-size:20px; font-family:courier; border:3px solid green; background-color:#000000;\">StarWars</a><br/><br/>");
		builderTextoEmail.append("<span style=\"font-size:10px; text-align:center;\">By: Jefferson da Silva Leal</span>");

		ObjetoEnviaEmail objetoEnviaEmail = new ObjetoEnviaEmail("drankhar@gmail.com", "Jefferson Da Silva Leal", "Testando Email com JAVA", builderTextoEmail.toString());
		
		objetoEnviaEmail.enviarEmail(true);
	}
}
