package ru.mts.emulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import java.util.Date;

@SpringBootApplication
@EnableJms
public class Application {

    static final String requestQueue = "DEV.QUEUE.1"; // A queue from the default MQ Developer container config

    static final String responseQueue = "DEV.QUEUE.2";

    public static void main(String[] args) {

        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        // Create the JMS Template object to control connections and sessions.
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

    }
}