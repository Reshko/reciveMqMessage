package ru.mts.emulator;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

import static ru.mts.emulator.Application.responseQueue;

@Component
public class Listener {

    @JmsListener(destination = Application.requestQueue)
    public void receiveMessage(Message msg, Session session) throws JMSException {

        String text;

        if (msg instanceof TextMessage){
            text = ((TextMessage) msg).getText();
        }
        else {
            text = msg.toString();
        }


        System.out.println();
        System.out.println("========================================");

        System.out.println("Responder received message: " + text);
        System.out.println("           Redelivery flag: " + msg.getJMSReplyTo());
        System.out.println("========================================");


        final String msgID = msg.getJMSMessageID();
        final String corrID = msg.getJMSCorrelationID();


        MessageProducer replyDest = session.createProducer(msg.getJMSDestination());
        TextMessage replyMsg = session.createTextMessage("Replying to " + text);
        replyMsg.setJMSCorrelationID(corrID);
        replyMsg.setJMSMessageID(msgID);
        replyDest.send(replyMsg);


    }
}
