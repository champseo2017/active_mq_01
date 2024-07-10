package com.example.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import jakarta.jms.DeliveryMode;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;

@Component
public class JmsMessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String destination, String message) {
        System.out.println("Attempting to send message: " + message + " to destination: " + destination);
        try {
            // Option 1: Use a new Session for each send
            jmsTemplate.send(destination, session -> createTextMessage(session, message));

            // Option 2: Use jmsTemplate.convertAndSend()
            // jmsTemplate.convertAndSend(destination, message);

            System.out.println("Message sent successfully");
        } catch (Exception e) {
            System.err.println("Error sending  message: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    private Message createTextMessage(Session session, String message) throws JMSException {
        Message textMessage = session.createTextMessage(message);
        textMessage.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
        return textMessage;
    }
}