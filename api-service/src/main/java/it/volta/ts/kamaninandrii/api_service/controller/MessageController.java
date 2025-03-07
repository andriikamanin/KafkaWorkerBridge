package it.volta.ts.kamaninandrii.api_service.controller;

import it.volta.ts.kamaninandrii.api_service.service.KafkaProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageController {
    private final KafkaProducerService producerService;

    public MessageController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        producerService.sendMessage("worker-topic", message);
        return "Сообщение отправлено!";
    }
}
