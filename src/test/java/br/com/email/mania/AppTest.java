package br.com.email.mania;

import java.util.Properties;

import org.junit.Test;

public class AppTest {

	@Test
	public void testeEmail() {
		
		/*Olhar as configurações do SMTP do seu email de origem*/
		Properties properties = new Properties();
		properties.put("mail.smtp.aith", "true");//Autorização
		properties.put("mail.smtp.starttls", "true");//Autenticação
		properties.put("mail.smtp.host", "smtp.gmail.com");// Servidor do gmail
		properties.put("mail.smtp.port", "456");// Porta padrão do servidor
		properties.put("main.smtp.socketFactory.prot", "465");//Especifica a porta a ser conectada pelo socket
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");//Classe socket de conexão ao SMTP
	}

}
