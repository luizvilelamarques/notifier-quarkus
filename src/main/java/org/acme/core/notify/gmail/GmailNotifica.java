package org.acme.core.notify.gmail;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.acme.core.CentralConfiguration;
import org.acme.core.bean.Bean;
import org.acme.core.notify.Notifier;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Named("gmail")
@Singleton
public class GmailNotifica implements Notifier<Bean> {

	@Inject
	CentralConfiguration controleSM;

	@ConfigProperty(name = "gmail.user")
	String user;

	@ConfigProperty(name = "gmail.password")
	String password;

	@Override
	public void notifica(Bean bean) {
		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		/** Ativa Debug para sessão */
		// session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(controleSM.getConfig().getCanalNotificacao(bean.getFila())));
			// Remetente
			Address[] toUser = InternetAddress.parse(controleSM.getConfig().getCanalNotificacao(bean.getFila()));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Notificação");// Assunto
			message.setText(bean.toString());
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
