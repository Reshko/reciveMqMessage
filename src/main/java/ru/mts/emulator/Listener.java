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

        final String msgID = msg.getJMSMessageID();
        final String corrID = msg.getJMSCorrelationID();

        Destination destination = session.createQueue(responseQueue);
        MessageProducer newDest = session.createProducer(destination);
        TextMessage responseMsg = session.createTextMessage("Replying to " + text);
        responseMsg.setJMSCorrelationID(corrID);
        responseMsg.setJMSMessageID(msgID);
        newDest.send(responseMsg);


    }
}
