package br.com.email.mania.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

//link para gerar a sua senha
// https://forum.uipath.com/t/send-smtp-mail-message-message-534-5-7-9-application-specific-password-required/461882
// https://forum.uipath.com/t/send-smtp-mail-message-message-534-5-7-9-application-specific-password-required/461882

public class ObjetoEnviaEmail {

	private String userName = "sasasdrankhar@gmail.com";
	private String password = "abcvajbckflluhuasgh";
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

	public void enviarEmailAnexo(boolean envioHtml) {
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

			// descrição do e-mail
			MimeBodyPart corpoEmail = new MimeBodyPart();

			if (envioHtml) {
				corpoEmail.setContent(mensagemEmail, "text/html; charset=utf-8");
			} else {
				corpoEmail.setText(mensagemEmail);
			}

			// anexo em pdf
			MimeBodyPart anexoEmail = new MimeBodyPart();
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(simuladorDePDF(), "apllication/pdf")));
			anexoEmail.setFileName("anexoemail.pdf");

			// Junta as duas partes do email
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(corpoEmail);
			multipart.addBodyPart(anexoEmail);

			message.setContent(multipart);

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enviarListaEmailAnexos(boolean envioHtml) {
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

			// descrição do e-mail
			MimeBodyPart corpoEmail = new MimeBodyPart();

			if (envioHtml) {
				corpoEmail.setContent(mensagemEmail, "text/html; charset=utf-8");
			} else {
				corpoEmail.setText(mensagemEmail);
			}
			
			// Lista de PDFs
			List<FileInputStream> arquivos = new ArrayList<FileInputStream>();
			arquivos.add(simuladorDePDF());
			arquivos.add(simuladorDePDF());
			arquivos.add(simuladorDePDF());
			arquivos.add(simuladorDePDF());

			// Junta as duas partes do email
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(corpoEmail);
			
			int index = 0;
			for (FileInputStream fileInputStream : arquivos) {
				// anexo em pdf
				MimeBodyPart anexoEmail = new MimeBodyPart();
				anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "application/pdf")));
				anexoEmail.setFileName("anexoemail"+index+".pdf");

				multipart.addBodyPart(anexoEmail);
				index++;
			}

			message.setContent(multipart);

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
		document.add(
				new Paragraph("Olá este é apenas um paragráfo teste se voçê está lendo ele é porque funcionou!!!"));
		document.close();
		return new FileInputStream(file);

	}

}
