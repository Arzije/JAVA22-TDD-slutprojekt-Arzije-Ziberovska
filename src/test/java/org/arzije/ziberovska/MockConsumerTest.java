package org.arzije.ziberovska;

import org.arzije.ziberovska.mockedObjects.MockConsumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MockConsumerTest {

    @Test
    @DisplayName("Test that run method starts the consumer")
    void testRun() {
        MockConsumer consumer = new MockConsumer();
        consumer.run();
        assertTrue(consumer.isRunning(), "Consumer should be running after run is called");
    }

    @Test
    @DisplayName("Test that stopRunning method stops the consumer")
    void testStopRunning() {
        MockConsumer consumer = new MockConsumer();
        consumer.run();
        consumer.stopRunning();
        assertFalse(consumer.isRunning(), "Consumer should not be running after stopRunning is called");
    }
}
