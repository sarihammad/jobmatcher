package com.devign.jobmatcher.job.kafka;

import com.devign.jobmatcher.job.dto.JobResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JobProducer {

    private final KafkaTemplate<String, JobResponse> kafkaTemplate;
    private static final String TOPIC = "job-submissions";

    public void sendJob(JobResponse job) {
        kafkaTemplate.send(TOPIC, job.getId().toString(), job);
        log.info("Kafka: Sent job to topic {} -> {}", TOPIC, job.getTitle());
    }
}