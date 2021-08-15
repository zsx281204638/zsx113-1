package com.itheima;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itheima.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class KafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }
    //1,关闭自动，2.设置手动提交模式 3 在消费者端 进行 确认
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        //配置手动提交offset
        factory.getContainerProperties().setAckMode((ContainerProperties.AckMode.MANUAL));
        return factory;
    }
    @RestController
    public class TestController{
        @Autowired
        private Producer producer;
            @GetMapping("/send1")
        public String send1() throws JsonProcessingException {
                producer.send1();
                return "ok";
            }
    }
//你在说什么
}
