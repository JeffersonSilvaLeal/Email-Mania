package br.com.email.mania;

import java.net.InterfaceAddress;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class AppTest {
	
	private String userName = "seuEmail";
	private String password = "suaSenha";
	//link para gerar a sua senha https://forum.uipath.com/t/send-smtp-mail-message-message-534-5-7-9-application-specific-password-required/461882
	//https://forum.uipath.com/t/send-smtp-mail-message-message-534-5-7-9-application-specific-password-required/461882
	@Test
	public void testeEmail() {
		
		try {
		/*Olhar as configurações do SMTP do seu email de origem*/
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");//Autorização
		properties.put("mail.smtp.starttls", "true");//Autenticação
		properties.put("mail.smtp.host", "smtp.gmail.com");// Servidor do gmail
		properties.put("mail.smtp.port", "465");// Porta padrão do servidor
		properties.put("mail.smtp.socketFactory.port", "465");//Especifica a porta a ser conectada pelo socket
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");//Classe socket de conexão ao SMTP
	
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
		
		Address[] toUser = InternetAddress.parse("taianfut@gmail.com, drankhar@gmail.com, waltermsbr@gmail.com");
	
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName));// Quem está enviando
		message.setRecipients(Message.RecipientType.TO, toUser);// Email de destino
		message.setSubject("Olá meu amigo Desenvolvedor");// Assunto do email
		message.setText("Olá Walter e Taian, estou testando o serviço do JavaMail chegou para voçês vlw?");
		
		Transport.send(message);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
