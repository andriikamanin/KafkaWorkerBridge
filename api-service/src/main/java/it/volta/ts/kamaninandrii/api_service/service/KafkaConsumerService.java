package it.volta.ts.kamaninandrii.api_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "response-topic", groupId = "api-group")
    public void listenResponse(String message) {
        logger.info("✅ [API] Получен ответ от Worker'а: {}", message);
    }
}