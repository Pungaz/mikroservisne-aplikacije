package rs.edu.raf.msa.statsservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rs.edu.raf.msa.statsservice.message.PlayerScore;

@Slf4j
@Service
public class StatsService {

	@Value("${rabbitmq.queue}")
	private String queueName;

	@Autowired
	RabbitTemplate template;

	// consumer
	@RabbitListener(queues = "${rabbitmq.queue}")
	public void listen(Message message) throws JsonProcessingException {

		log.info("Received message - {}", message.toString());

		ObjectMapper objectMapper = new ObjectMapper();

		String stringMessage = new String(message.getBody());

		if (stringMessage.contains("gameId")) {
			log.info("PlayerScore Message read from {} : {}", queueName, objectMapper.readValue(stringMessage, PlayerScore.class).toString());
		} else {
			log.info("Hello World Message read from {} : {}", queueName, stringMessage);
		}
	}

	// producer
	@Scheduled(fixedDelay = 5000)
	public void convertAndSend() {

		template.convertAndSend(queueName, "Hello, world!");

		template.convertAndSend(queueName, PlayerScore.builder()
													  .player("Derrick Rose")
													  .gameId(1L)
													  .points(2)
													  .build());
	}

}
