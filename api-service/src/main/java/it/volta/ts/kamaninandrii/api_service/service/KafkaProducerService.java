package it.volta.ts.kamaninandrii.api_service.service;

import it.volta.ts.kamaninandrii.api_service.model.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaProducerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    private final KafkaTemplate<String, Message> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, Message message) {
        logger.info("LOLOS");
        logger.info("📤 [API] Отправка сообщения в Kafka -> Топик: {}, Сообщение: {}", topic, message);
        kafkaTemplate.send(topic, message);
    }
}
