package com.github.tantalor93.rest;

import com.github.tantalor93.dto.Feedback;
import com.github.tantalor93.service.FeedbackConsumerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackConsumerController {

    private final FeedbackConsumerService feedbackConsumerService;

    public FeedbackConsumerController(final FeedbackConsumerService feedbackConsumerService) {
        this.feedbackConsumerService = feedbackConsumerService;
    }

    @GetMapping(value = "/feedbacks/consume")
    public ResponseEntity<Feedback> consume() {
        final Feedback feedback = feedbackConsumerService.consumeFromQueue();
        if (feedback == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(feedback);
    }

}
