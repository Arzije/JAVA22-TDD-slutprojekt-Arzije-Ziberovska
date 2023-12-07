package org.arzije.ziberovska;

import org.arzije.ziberovska.mockedObjects.ConsumerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConsumerTest {

    @Test
    @DisplayName("Test that run method starts the consumer")
    void testRun() {
        ConsumerImpl consumer = new ConsumerImpl();
        consumer.run();
        assertTrue(consumer.isRunning(), "Consumer should be running after run is called");
    }

    @Test
    @DisplayName("Test that stopRunning method stops the consumer")
    void testStopRunning() {
        ConsumerImpl consumer = new ConsumerImpl();
        consumer.run();
        consumer.stopRunning();
        assertFalse(consumer.isRunning(), "Consumer should not be running after stopRunning is called");
    }
}
