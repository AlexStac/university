package ru.digitalleague.university.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class logListener{

    /**
     * Метод слушает определённую очередь
     * @param message сообщение из очереди
     * */
    @SneakyThrows
    @RabbitListener(queues = "logging")
    public void processQueue(String message) {

        log.info("Received from queue:\n" + message);
    }
}
