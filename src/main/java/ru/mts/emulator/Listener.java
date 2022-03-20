package ru.mts.emulator;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class Listener {

    @JmsListener(destination = Application.requestQueue)
    public void receiveMessage(Message msg) throws JMSException {

        System.out.println();
        System.out.println("========================================");
        System.out.println("Received message is: " + msg.getBody(String.class));
        System.out.println("========================================");



    }
}
