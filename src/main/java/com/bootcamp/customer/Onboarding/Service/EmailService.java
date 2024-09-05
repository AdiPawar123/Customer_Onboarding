package com.bootcamp.customer.Onboarding.Service;

import com.bootcamp.customer.Onboarding.model.User;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private MailjetClient mailjetClient;

    public void sendEmail() {
        JSONObject email = new JSONObject();
        email.put("From", new JSONObject().put("Email","unitel.mail.service@gmail.com"));
        email.put("To", new JSONObject().put("Email", "pawar.adi.105@gmail.com"));
        email.put("Subject", "Your Subject Here");
        email.put("TextPart", "Your email body text here");

        MailjetRequest request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray().put(email));

        try {
            MailjetResponse response = mailjetClient.post(request);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed to send email. Status code: " + response.getStatus());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error sending email", e);
        }
    }


}
