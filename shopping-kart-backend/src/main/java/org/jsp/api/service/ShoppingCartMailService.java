package org.jsp.api.service;

import org.jsp.api.dto.EmailConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class ShoppingCartMailService {
	@Autowired
	private JavaMailSender javaMailSender;

	public String sendMail(EmailConfiguration configuration) {

		if (configuration.getUser().get("email") != null) {
			try {
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message);
				helper.setTo(configuration.getUser().get("email"));
				helper.setSubject(configuration.getSubject());
				helper.setText(configuration.getText());
				javaMailSender.send(message);
				return "mail sent successfully";
			} catch (MailException | MessagingException e) {
				e.printStackTrace();
				return "Unable to send email";
			}
		} else {
			return "Unable to send email";
		}
	}
}
