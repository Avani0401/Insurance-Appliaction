package com.insurance.service;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.insurance.entity.EmailRequest;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailService.
 */
@Service
public class EmailService {

	/** The sendgrid. */
	@Autowired
	SendGrid sendgrid;
	
	/** The logger. */
	private static Logger logger =LogManager.getLogger(EmailService.class);
	

	/** The from. */
	@Value("${sendgrid.email}")
	private String from;

	/**
	 * Send email.
	 *
	 * @param emailrequest the emailrequest
	 * @return the response
	 */
	public Response sendEmail(EmailRequest emailrequest) {
		Mail mail = new Mail(new Email(from), emailrequest.getSubject(), new Email(emailrequest.getTo()),
				new Content("text/plain", emailrequest.getBody()));
		mail.setReplyTo(new Email(emailrequest.getTo()));
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			return this.sendgrid.api(request);
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.debug(ex.getMessage());
		}
		return null;
	}
}
