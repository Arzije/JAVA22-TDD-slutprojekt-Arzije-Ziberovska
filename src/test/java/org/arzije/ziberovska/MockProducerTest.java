package org.arzije.ziberovska;

import org.arzije.ziberovska.mockedObjects.MockProducer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class MockProducerTest {

    @Test
    @DisplayName("Test that run method starts the producer")
    void testRun() {
        MockProducer producer = new MockProducer();
        producer.run();
        assertTrue(producer.isRunning(), "Producer should be running after run is called");
    }

    @Test
    @DisplayName("Test that stopRunning method stops the producer")
    void testStopRunning() {
        MockProducer producer = new MockProducer();
        producer.run();
        producer.stopRunning();
        assertFalse(producer.isRunning(), "Producer should not be running after stopRunning is called");
    }

}
