package br.com.email.mania.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

//link para gerar a sua senha
// https://forum.uipath.com/t/send-smtp-mail-message-message-534-5-7-9-application-specific-password-required/461882
// https://forum.uipath.com/t/send-smtp-mail-message-message-534-5-7-9-application-specific-password-required/461882

public class ObjetoEnviaEmail {

	private String userName = "seuEmail";
	private String password = "suaSenha";
	private String listaDestinatarios = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String mensagemEmail = "";

	public ObjetoEnviaEmail(String listaDestinatario, String nomeRementente, String assuntoEmail,
			String mensagemEmail) {
		this.listaDestinatarios = listaDestinatario;
		this.nomeRemetente = nomeRementente;
		this.assuntoEmail = assuntoEmail;
		this.mensagemEmail = mensagemEmail;

	}

	public void enviarEmail(boolean envioHtml) {
		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.ssl.trust", "*");
			properties.put("mail.smtp.auth", "true");// Autorização
			properties.put("mail.smtp.starttls", "true");// Autenticação
			properties.put("mail.smtp.host", "smtp.gmail.com");// Servidor do gmail
			properties.put("mail.smtp.port", "465");// Porta padrão do servidor
			properties.put("mail.smtp.socketFactory.port", "465");// Especifica a porta a ser conectada pelo socket
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");// Classe socket de
																								// conexão ao
																								// SMTP

			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			});

			Address[] toUser = InternetAddress.parse(listaDestinatarios);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName, nomeRemetente));// Quem está enviando
			message.setRecipients(Message.RecipientType.TO, toUser);// Email de destino
			message.setSubject(assuntoEmail);// Assunto do email

			if (envioHtml) {
				message.setContent(mensagemEmail, "text/html; charset=utf-8");
			} else {
				message.setText(mensagemEmail);
			}

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Esse método simula um PDF ou qualquer arquivo enviado em anexo por email
	 * Retorna um PDF em branco com o texto do paragráfo de exemplo
	 */
	private FileInputStream simuladorDePDF() throws Exception {
		Document document = new Document();
		File file = new File("ArquivoTeste.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteudo em anexo em JavaMail"));
		document.close();
		return new FileInputStream(file);

	}
}
