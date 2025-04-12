# OV Kafka Consumer/Producer
Standardized library for Producer and Consumer configuration.

### Usage
- Topics are available using `TopicConstant.TOPIC_NAME`
- `Producer` is a Spring Component, and can be used as follows `Producer.producer(T t, String servers, String topic)`.
  - `T` is the object to be sent to the broker;
  - `servers` are the listening brokers;
  - `topic` the topic to add the new message;
  - Sends `Json` `K, V` using `Kafka` `JsonSerializer`.