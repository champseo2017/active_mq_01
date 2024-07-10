package com.example.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JmsMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(JmsMessageListener.class);

    @JmsListener(destination = "example.queue")
    public void receiveMessage(Message message) throws JMSException {
        try {
            if (message instanceof TextMessage) {
                String messageBody = ((TextMessage) message).getText();
                logger.info("JmsMessageListener received message: " + messageBody);
                // ประมวลผลข้อความตามต้องการ
            } else {
                logger.warn("Received message of unexpected type: " + message.getClass().getName());
            }

            // Acknowledge ข้อความเมื่อประมวลผลเสร็จ
            message.acknowledge();
        } catch (Exception e) {
            logger.error("Error processing message: " + e.getMessage(), e);
            // ไม่ acknowledge ข้อความเมื่อเกิดข้อผิดพลาด
            throw new JMSException("Failed to process message: " + e.getMessage());
        }
    }
}
