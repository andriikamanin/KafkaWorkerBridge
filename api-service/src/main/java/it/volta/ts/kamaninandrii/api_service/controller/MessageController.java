package it.volta.ts.kamaninandrii.api_service.controller;

import it.volta.ts.kamaninandrii.api_service.model.Message;
import it.volta.ts.kamaninandrii.api_service.service.KafkaProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageController {
    private final KafkaProducerService producerService;

    public MessageController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    // Обновленный метод для отправки JSON-сообщений
    @PostMapping("/send")
    public String sendMessage(@RequestBody Message message) {
        producerService.sendMessage("worker-topic", message);
        return "Сообщение отправлено!";
    }
}