package io.github.jotabrc.ov_kafka_cp.broker;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Component
public class Consumer {

    protected Properties getProperties(String servers) {
        Properties props = new Properties();

        props.put("bootstrap.servers", servers);
        props.put("key.deserializer", "org.springframework.kafka.support.serializer.JsonDeserializer");
        props.put("value.deserializer", "org.springframework.kafka.support.serializer.JsonDeserializer");

        return props;
    }

    public void consumer(String servers, String... topics) {
        KafkaConsumer<String, String> consumer = null;
        try {
            consumer = new KafkaConsumer<>(getProperties(servers));
            consumer.subscribe(Arrays.asList(topics));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                records.forEach(record -> System.out.println(record.value()));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (consumer != null)
                consumer.close();
        }
    }
}
