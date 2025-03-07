package it.volta.ts.kamaninandrii.worker_service.service;

import it.volta.ts.kamaninandrii.worker_service.model.Message;
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

    public void sendResponse(Message message) {
        logger.info("📤 [Worker] Отправка ответа в API -> Топик: response-topic, Сообщение: {}", message);
        kafkaTemplate.send("response-topic", message);
    }
}