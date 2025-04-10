package io.github.jotabrc.ov_kafka_cp.broker;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

@Component
public abstract class Consumer {

    @Autowired
    protected BrokerConfig brokerConfig;

    private Properties getProperties() {
        Properties props = new Properties();

        Optional<String> servers = Optional.ofNullable(brokerConfig.getBootstrapServers());
        Optional<String> key = Optional.ofNullable(brokerConfig.getKeyDeserializer());
        Optional<String> value = Optional.ofNullable(brokerConfig.getValueDeserializer());

        props.put("bootstrap.servers", servers.orElse("localhost:9092"));
        props.put("key.deserializer", key.orElse("org.springframework.kafka.support.serializer.JsonDeserializer"));
        props.put("value.deserializer", value.orElse("org.springframework.kafka.support.serializer.JsonDeserializer"));

        return props;
    }

    public void consumer(String... topics) {
        KafkaConsumer<String, String> consumer = null;
        try {
            consumer = new KafkaConsumer<>(getProperties());
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
