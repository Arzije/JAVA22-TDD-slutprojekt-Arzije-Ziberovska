package org.arzije.ziberovska;

import org.arzije.ziberovska.mockedObjects.ProducerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ProducerTest {

    @Test
    @DisplayName("Test that run method starts the producer")
    void testRun() {
        ProducerImpl producer = new ProducerImpl();
        producer.run();
        assertTrue(producer.isRunning(), "Producer should be running after run is called");
    }

    @Test
    @DisplayName("Test that stopRunning method stops the producer")
    void testStopRunning() {
        ProducerImpl producer = new ProducerImpl();
        producer.run();
        producer.stopRunning();
        assertFalse(producer.isRunning(), "Producer should not be running after stopRunning is called");
    }

}
