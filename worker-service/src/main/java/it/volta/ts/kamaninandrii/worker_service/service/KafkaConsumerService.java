package it.volta.ts.kamaninandrii.worker_service.service;

import it.volta.ts.kamaninandrii.worker_service.model.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private final KafkaProducerService kafkaProducerService;

    public KafkaConsumerService(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @KafkaListener(topics = "worker-topic", groupId = "worker-group")
    public void listen(Message message) {
        logger.info("📥 [Worker] Получено сообщение из Kafka: {}", message);

        try {
            // Лог начала обработки
            logger.info("🔄 [Worker] Начинаем обработку сообщения...");

            // Имитация обработки
            String response = "Обработано: " + message.getContent();
            logger.info("✅ [Worker] Сообщение успешно обработано: {}", response);

            // Создаем ответное сообщение
            Message responseMessage = new Message(response);

            // Отправляем результат обратно в API
            logger.info("📤 [Worker] Отправка ответа в Kafka: {}", responseMessage);
            kafkaProducerService.sendResponse(responseMessage);
            logger.info("🚀 [Worker] Ответ успешно отправлен!");

        } catch (Exception e) {
            logger.error("❌ [Worker] Ошибка при обработке сообщения: {}", message, e);
        }
    }


}
