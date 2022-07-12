package ge.odvali;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class TestProducer {

    private Producer<Long, String> producer;
    private String topic;

    public TestProducer(Producer<Long, String> producer, String topic) {
        this.producer = producer;
        this.topic = topic;
    }

    public void send(String message) {
        ProducerRecord<Long, String> producerRecord = new ProducerRecord<>(topic, message);
        producer.send(producerRecord);
    }
}
