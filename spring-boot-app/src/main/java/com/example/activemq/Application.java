package com.example.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private JmsMessageSender jmsMessageSender;

    @Autowired
    private JmsTemplate jmsTemplate;

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Checking ActiveMQ connection...");
        try {
            jmsTemplate.execute(session -> {
                logger.info("ActiveMQ connection successful");
                logger.info("ActiveMQ session: " + session);
                return null;
            });
        } catch (Exception e) {
            logger.error("Error connecting to ActiveMQ: " + e.getMessage(), e);
            return;
        }

        logger.info("Sending message to ActiveMQ...");
        try {
            jmsMessageSender.sendMessage("example.queue", "Hello, ActiveMQ!");
            logger.info("Message sent to ActiveMQ");
        } catch (Exception e) {
            logger.error("Error sending message: " + e.getMessage(), e);
        }

        // Add a delay to allow time for message processing
        Thread.sleep(5000);

        logger.info("Checking if message was received...");
        try {
            String receivedMessage = jmsTemplate.receiveAndConvert("example.queue").toString();
            logger.info("Received message: " + receivedMessage);
        } catch (Exception e) {
            logger.error("Error receiving message: " + e.getMessage(), e);
        }
    }
}
