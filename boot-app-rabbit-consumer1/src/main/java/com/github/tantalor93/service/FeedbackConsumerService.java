package com.github.tantalor93.service;

import com.github.tantalor93.dto.Feedback;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class FeedbackConsumerService {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public FeedbackConsumerService(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public Feedback consumeFromQueue() {
        System.out.println(queue);
        return (Feedback) rabbitTemplate.receiveAndConvert(queue.getName());
    }
}
