package it.volta.ts.kamaninandrii.worker_service.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "worker-topic", groupId = "worker-group")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Получено сообщение: " + record.value());

    }
}
