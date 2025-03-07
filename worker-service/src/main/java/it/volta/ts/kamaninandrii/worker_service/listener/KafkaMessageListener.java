package it.volta.ts.kamaninandrii.worker_service.listener;

import it.volta.ts.kamaninandrii.worker_service.model.Message;
import it.volta.ts.kamaninandrii.worker_service.service.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);
    private final KafkaProducerService kafkaProducerService;

    public KafkaMessageListener(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    // Подписка на топик "worker-topic"
    @KafkaListener(topics = "worker-topic", groupId = "worker-group")
    public void onMessage(Message message) {
        try {
            if (message != null) {
                logger.info("👨‍💻 [Worker] Получено сообщение: {}", message);
                processMessage(message);
            } else {
                logger.error("❌ [Worker] Получено пустое сообщение");
            }
        } catch (Exception e) {
            logger.error("❌ [Worker] Ошибка при обработке сообщения", e);
        }
    }

    private void processMessage(Message message) {
        try {
            logger.info("🔄 [Worker] Начинаем обработку сообщения...");

            // Имитация обработки сообщения
            String processedContent = "Обработано: " + message.getContent();
            logger.info("✅ [Worker] Сообщение успешно обработано: {}", processedContent);

            // Создаем объект ответа
            Message responseMessage = new Message(processedContent);

            // Логируем перед отправкой
            logger.info("📤 [Worker] Отправка ответа в Kafka: {}", responseMessage);

            // Отправляем обработанное сообщение в другой Kafka-топик
            kafkaProducerService.sendResponse(responseMessage);

            logger.info("🚀 [Worker] Ответ успешно отправлен!");
        } catch (Exception e) {
            logger.error("❌ [Worker] Ошибка при обработке сообщения", e);
        }
    }
}