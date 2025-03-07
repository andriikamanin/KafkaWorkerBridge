package it.volta.ts.kamaninandrii.worker_service.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private final KafkaProducerService kafkaProducerService;

    public KafkaConsumerService(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @KafkaListener(topics = "worker-topic", groupId = "worker-group")
    public void listen(ConsumerRecord<String, String> record) {
        logger.info("📥 [Worker] Получено сообщение из Kafka: {}", record.value());

        // Имитация обработки
        String response = "Обработано: " + record.value();
        logger.info("🔄 [Worker] Обработка завершена: {}", response);

        // Отправляем результат обратно в API
        kafkaProducerService.sendResponse(response);
    }
}
