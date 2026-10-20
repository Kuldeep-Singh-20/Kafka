package com.consumer.consumer;

import com.consumer.dto.EmployeeDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Component
public class KafkaConsumer {

//    Kafka mein topic ka naam ek constant variable mein store karne ke liye use hoti hai.
    public static final String EMPLOYEE_TOPIC="employee_topic";

//    ObjectMapper is used for Kafka se aane wale JSON String ko Java ke
//    EmployeeDto object mein convert karne ke liye hota hai.
    private final ObjectMapper objectMapper;

    public KafkaConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = EMPLOYEE_TOPIC)
    public void consume(String message){

        EmployeeDto employeeDto;
        try{
            employeeDto= objectMapper.readValue(message, EmployeeDto.class);
        } catch (JacksonException e) {
            throw new RuntimeException(e);
        }

        System.out.println(employeeDto.getId());
        System.out.println(employeeDto.getName());
        System.out.println(employeeDto.getEmail());
        System.out.println(employeeDto.getCompany());

        System.out.println(employeeDto.getAddress());

    }
}
