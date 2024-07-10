package com.example.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private JmsMessageSender jmsMessageSender;

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Checking ActiveMQ connection...");
        try {
            jmsTemplate.execute(session -> {
                System.out.println("ActiveMQ connection successful");
                System.out.println("ActiveMQ session: " + session);
                return null;
            });
        } catch (Exception e) {
            System.err.println("Error connecting to ActiveMQ: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        System.out.println("Sending message to ActiveMQ...");
        try {
            jmsMessageSender.sendMessage("example.queue", "Hello, ActiveMQ!");
            System.out.println("Message sent to ActiveMQ");
        } catch (Exception e) {
            System.err.println("Error  sending message: " + e.getMessage());
            e.printStackTrace();
        }

        // ลบส่วนการรับข้อความและการ delay ออก
    }
}