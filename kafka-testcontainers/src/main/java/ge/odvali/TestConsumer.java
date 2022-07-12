package ge.odvali;


import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TestConsumer implements Runnable {

    private Consumer<Long, String> consumer;
    private Long pollDuration;
    private List<String> messagesHistory;
    private boolean closed;

    public TestConsumer(Consumer<Long, String> consumer, Long pollDuration) {
        this.consumer = consumer;
        this.pollDuration = pollDuration;
        messagesHistory = new ArrayList<>();
    }

    @Override
    public void run() {
        while (!closed) {
            ConsumerRecords<Long, String> records = consumer.poll(Duration.ofMillis(pollDuration));
            records.forEach(rec -> {
                messagesHistory.add(rec.value());
            });
        }
    }

    public List<String> getMessageHistory() {
        return messagesHistory;
    }

    public void close() {
        closed = true;
    }
}
