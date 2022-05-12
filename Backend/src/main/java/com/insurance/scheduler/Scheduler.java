package com.insurance.scheduler;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.insurance.dao.RoleDao;
import com.insurance.dao.UserDao;
import com.insurance.entity.EmailRequest;
import com.insurance.entity.PolicyApproved;
import com.insurance.entity.PolicyHolder;
import com.insurance.entity.User;
import com.insurance.service.EmailService;
import com.insurance.service.PolicyHolderService;
import com.insurance.service.UnderwriterService;

// TODO: Auto-generated Javadoc
/**
 * The Class Scheduler.
 */
@Component
public class Scheduler {
	
	/** The policy holder service. */
	@Autowired
	private PolicyHolderService policyHolderService;
	
	/** The user dao. */
	@Autowired
	private UserDao userDao;
	
	
	/** The email service. */
	@Autowired
	private EmailService emailService;
	
	/** The underwriter service. */
	@Autowired
	private UnderwriterService underwriterService;
	
	/** The format. */
	DateTimeFormatter format =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
	/**
	 * Gets the date from string.
	 *
	 * @param string the string
	 * @param format the format
	 * @return the date from string
	 */
	public LocalDate getDateFromString(String string, DateTimeFormatter format)
	{
	
	// Converting the string to date
	// in the specified format
	LocalDate date = LocalDate.parse(string, format);
	
	// Returning the converted date
	return date;
	}

	/**
	 * Premium email.
	 */
	@Scheduled(cron = "0 0 0 * * *")
	public void premiumEmail() {

			List<PolicyHolder> monthlyPolicyHolders;
			
			System.out.println("Hello email");
			try {
				monthlyPolicyHolders = this.policyHolderService.monthlyPolicyHolder();
				System.out.println(monthlyPolicyHolders);
				for(PolicyHolder p : monthlyPolicyHolders) {
					int duration= Period.between(getDateFromString(p.getBuyOn(),format), LocalDate.now()).getDays();
					
					if(duration%28 == 0 && duration != 0 && p.getPolicyApproved() == PolicyApproved.APPROVED) {
						User user = this.userDao.findByPolicyHolder(p);
						String email = user.getEmail();
						EmailRequest emailRequest = new EmailRequest();
						emailRequest.setTo(email);
						emailRequest.setSubject("Payment Due");
						emailRequest.setBody("Your payment of "+ p.getPolicyHolderPremium()+" is due");
			
						this.emailService.sendEmail(emailRequest);
					}
					
					System.out.println("Premium Email");
				}
				
			} catch (Exception e) {
				e.getMessage();
			}
			
			List<PolicyHolder> yearlyPolicyHolders;
			
			try {
				yearlyPolicyHolders = this.policyHolderService.yearlyPolicyHolder();
				for(PolicyHolder p : yearlyPolicyHolders) {
					int duration= Period.between(getDateFromString(p.getBuyOn(),format), LocalDate.now()).getDays();
					
					if(duration%365 == 0 && duration != 0 && p.getPolicyApproved() == PolicyApproved.APPROVED) {
						User user = this.userDao.findByPolicyHolder(p);
						String email = user.getEmail();
						EmailRequest emailRequest = new EmailRequest();
						emailRequest.setTo(email);
						emailRequest.setSubject("Payment Due");
						emailRequest.setBody("Your payment of "+ p.getPolicyHolderPremium()+" is due");
			
						this.emailService.sendEmail(emailRequest);
					}
					
					System.out.println("Premium Email");
				}
				
			} catch (Exception e) {
				e.getMessage();
			}
			
		
		
	}
	
	/**
	 * Underwriter email.
	 */
	@Scheduled(cron = "0 0 4 * * *")
	public void underwriterEmail() {
		List<User> underwriters;
		try {
			underwriters = this.underwriterService.allUnderwriterList();
			String body = "";
			
			List<PolicyHolder> policyholders = this.policyHolderService.allPolicyHolder();
			
			for(PolicyHolder p: policyholders) {
				body+=p.getUser().getUserName();
				if(p.getMedical() != null || "".equals(p.getMedical()))
					body+=" has "+ p.getMedical() + " medical conditions.\n" ;
				if(p.getAccidents() != null ||"".equals(p.getAccidents()))
					body+=" has gone through "+ p.getAccidents() + " accidents.\n" ;
				body+=p.getPolicyApproved();
			}
			for(User u : underwriters) {
				EmailRequest emailRequest = new EmailRequest();
				String email = u.getEmail()	;
				emailRequest.setTo(email);
				emailRequest.setSubject("PolicyHolder List");
				emailRequest.setBody(body);
		
				this.emailService.sendEmail(emailRequest);
			
			}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
