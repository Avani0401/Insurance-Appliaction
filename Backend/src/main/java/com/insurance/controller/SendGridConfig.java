package com.insurance.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

// TODO: Auto-generated Javadoc
/**
 * The Class SendGridConfig.
 */
@Configuration
public class SendGridConfig {
	
	/** The key. */
	@Value("${sendgrid.key}")
	private String key;
	
	/**
	 * Gets the sendgrid.
	 *
	 * @return the sendgrid
	 */
	@Bean
	public SendGrid getSendgrid() {
		return new SendGrid(key);
	}
	
}
