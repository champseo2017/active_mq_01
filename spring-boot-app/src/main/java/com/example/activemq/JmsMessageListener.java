package com.example.activemq;

// import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
// import jakarta.jms.JMSException;
// import jakarta.jms.Message;
// import jakarta.jms.TextMessage;

@Component
public class JmsMessageListener {

    // @JmsListener(destination = "example.queue")
    // public void receiveMessage(Message message) throws JMSException {
    //     try {
    //         if (message instanceof TextMessage) {
    //             String messageBody = ((TextMessage) message).getText();
    //             System.out.println("JmsMessageListener received message: " + messageBody);
    //             // ประมวลผลข้อความตามต้องการ
    //         } else {
    //             System.out.println("Received message of unexpected type: " + message.getClass().getName());
    //         }
            
    //         // Acknowledge ข้อความเมื่อประมวลผลเสร็จ
    //         message.acknowledge();
    //     } catch (Exception e) {
    //         System.err.println("Error processing message: " + e.getMessage());
    //         e.printStackTrace();
    //         // ไม่ acknowledge ข้อความเมื่อเกิดข้อผิดพลาด
    //         throw new JMSException("Failed to process message: " + e.getMessage());
    //     }
    // }
}