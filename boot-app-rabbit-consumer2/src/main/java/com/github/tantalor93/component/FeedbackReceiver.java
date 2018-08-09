package com.github.tantalor93.component;

import com.github.tantalor93.dto.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class FeedbackReceiver {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackReceiver.class);

    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue(value = "boot-app-rabbit-consumer", durable = "false"),
            exchange = @Exchange(name = "boot-app-exchange", type = "topic"),
            key = "feedback.#"
    )
    )
    public void handleFeedback(Feedback feedback, @Header("amqp_receivedRoutingKey") String routingKey) {
        logger.info("RECEIVED: feedback={} with key={}", feedback, routingKey);
    }
}
