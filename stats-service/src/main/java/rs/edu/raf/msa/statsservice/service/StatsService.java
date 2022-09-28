package rs.edu.raf.msa.statsservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.edu.raf.msa.statsservice.message.GamesAreFinishedParsingMessage;

import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatsService {

    @Value("${rabbitmq.queue}")
    private String queueName;

    final RabbitTemplate template;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void listen(Message message) throws JsonProcessingException {
        log.info("Received message - {}", new String(message.getBody()));

        // Tried mapping the object but it doesn't work,
//        ObjectMapper objectMapper = new ObjectMapper();
//        String stringMessage = new String(message.getBody());
//        log.info("QueName is: {} and the message is: {}", queueName, objectMapper.readValue(stringMessage, GamesAreFinishedParsingMessage.class).toString());
    }

}
