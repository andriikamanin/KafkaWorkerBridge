package it.volta.ts.kamaninandrii.api_service.listener;

import it.volta.ts.kamaninandrii.api_service.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "response-topic", groupId = "api-group")
    public void onMessage(Message message) {
        try {
            logger.info("📥 [API] Получено сообщение: {}", message);
            processMessage(message);
        } catch (Exception e) {
            logger.error("❌ [API] Ошибка при обработке сообщения: {}", message, e);
        }
    }

    private void processMessage(Message message) {
        try {
            logger.info("🔧 [API] Начинаем обработку сообщения: {}", message.getContent());

            // Эмуляция обработки
            String processedData = message.getContent().toUpperCase(); // Например, просто делаем текст заглавным
            logger.info("✅ [API] Обработанные данные: {}", processedData);

            // Можно сохранить результат в базу данных (если у тебя есть JPA/Repository)
            // messageRepository.save(new ProcessedMessage(processedData));

            // Можно передать результат в другой сервис через REST API
            // restTemplate.postForObject("http://external-service/api/messages", processedData, String.class);

            logger.info("🚀 [API] Обработка завершена успешно!");
        } catch (Exception e) {
            logger.error("❌ [API] Ошибка при обработке сообщения: {}", message.getContent(), e);
        }
    }
}