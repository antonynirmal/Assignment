package my.sample.services.impl;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import my.sample.models.Info;

	@Component
	public class JMSReceiver {

	    @JmsListener(destination = "db", containerFactory = "InfoFactory")
	    public void receiveMessage(Info info) {
	        System.out.println("Received <" + info + ">");
	    }
	}