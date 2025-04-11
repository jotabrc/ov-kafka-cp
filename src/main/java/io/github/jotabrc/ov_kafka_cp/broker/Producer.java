package io.github.jotabrc.ov_kafka_cp.broker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jotabrc.ovauth.token.SecurityHeader;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Properties;

@Component
public class Producer {

    protected Properties getProperties(String servers) {
        Properties props = new Properties();

        props.put("bootstrap.servers", servers);
        props.put("key.serializer", "org.springframework.kafka.support.serializer.JsonSerializer");
        props.put("value.serializer", "org.springframework.kafka.support.serializer.JsonSerializer");

        return props;
    }

    public <T> void producer(T t, String servers, String topic) throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException {
        KafkaProducer<String, String> producer = new KafkaProducer<>(getProperties(servers));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(t);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "key", json);

        String data = Instant.now().toString();
        String token = SecurityHeader.create(data);

        Header secureOrigin = new RecordHeader(io.github.jotabrc.ovauth.header.Header.X_SECURE_ORIGIN.getHeader(), token.getBytes(StandardCharsets.UTF_8));
        Header secureData = new RecordHeader(io.github.jotabrc.ovauth.header.Header.X_SECURE_DATA.getHeader(), data.getBytes(StandardCharsets.UTF_8));
        record.headers().add(secureOrigin);
        record.headers().add(secureData);

        producer.send(record);
        producer.close();
    }
}
