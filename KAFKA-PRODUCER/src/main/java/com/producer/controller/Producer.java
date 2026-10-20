package com.producer.controller;

import com.producer.dto.EmployeeDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/producer")
public class Producer {

    //this constant is used to pushing event into kafka topic.
    public static final String EMPLOYEE_TOPIC="employee_topic";

//    Kafka ko message send karne ke liye.
    private final KafkaTemplate<String, Object> kafkaTemplate;

//    ObjectMapper → Java object ko JSON String mein convert karne ke liye.
    private final ObjectMapper objectMapper;

    public Producer(KafkaTemplate<String, Object> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    @PostMapping("/publish")
    public String publishMessageToKafka(@RequestBody EmployeeDto employeeDto ) {

        try{
            String employeeAsString=objectMapper.writeValueAsString(employeeDto);
            kafkaTemplate.send(EMPLOYEE_TOPIC,employeeAsString);
        } catch (JacksonException e) {
            throw new RuntimeException(e);
        }
        return "Message send to kafka successfully";
    }



}
