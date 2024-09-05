package com.bootcamp.customer.Onboarding.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    public void sendLoginCongfirmation(String to){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("pawar.adi105@gmail.com");
        message.setSubject("Login Successful");
        message.setText("Successful Login");
        sender.send(message);

    }


}
