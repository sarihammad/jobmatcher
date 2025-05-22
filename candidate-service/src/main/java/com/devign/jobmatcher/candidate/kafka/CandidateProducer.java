package com.devign.jobmatcher.candidate.kafka;

import com.devign.jobmatcher.candidate.dto.CandidateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CandidateProducer {

    private final KafkaTemplate<String, CandidateResponse> kafkaTemplate;
    private static final String TOPIC = "candidate-submissions";

    public void sendCandidate(CandidateResponse candidate) {
        kafkaTemplate.send(TOPIC, candidate.getId().toString(), candidate);
        log.info("Kafka: Sent candidate to topic {} -> {}", TOPIC, candidate.getFullName());
    }
}