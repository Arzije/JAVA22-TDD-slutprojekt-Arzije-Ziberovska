package org.arzije.ziberovska;

import org.arzije.ziberovska.mockedObjects.MockBuffer;
import org.arzije.ziberovska.mockedObjects.MockConsumer;
import org.arzije.ziberovska.mockedObjects.MockItem;
import org.arzije.ziberovska.mockedObjects.MockProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BufferTest {

    private MockBuffer buffer;
    private MockProducer producer;
    private MockConsumer consumer;

    @BeforeEach
    void setUp() {
        buffer = new MockBuffer();
        producer = new MockProducer(buffer);
        consumer = new MockConsumer(buffer);
    }

    @Test
    @DisplayName("Test producer adds item to buffer")
    void testProducerAddsItem() {
        producer.run();
        assertFalse(buffer.getBufferList().isEmpty(), "Buffer should not be empty after producer runs");
    }

    @Test
    @DisplayName("Test consumer removes item from buffer")
    void testConsumerRemovesItem() {
        producer.run();
        consumer.run();
        assertTrue(buffer.getBufferList().isEmpty(), "Buffer should be empty after consumer runs");
    }

    @Test
    @DisplayName("Test removing single item from buffer")
    void testRemoveSingleItem() {
        producer.run();
        assertFalse(buffer.getBufferList().isEmpty(), "Buffer should not be empty after producer runs");

        consumer.run();
        assertTrue(buffer.getBufferList().isEmpty(), "Buffer should be empty after consumer runs");
    }

    @Test
    @DisplayName("Test adding multiple items and then removing them")
    void testAddingAndRemovingMultipleItems() {
        int numberOfItemsToAdd = 10;
        for (int i = 0; i < numberOfItemsToAdd; i++) {
            producer.run();
        }
        assertEquals(numberOfItemsToAdd, buffer.getBufferSize(), "Buffer should have 5 items");

        for (int i = 0; i < numberOfItemsToAdd; i++) {
            consumer.run();
        }
        assertTrue(buffer.getBufferList().isEmpty(), "Buffer should be empty after removing all items");
    }

    @Test
    @DisplayName("Testing thread safety with multiple producers and consumers")
    void testThreadSafetyWithMultipleProducersAndConsumers() throws InterruptedException {
        int numberOfProducers = 3;
        int numberOfConsumers = 3;
        int itemsPerProducer = 10;

        Thread[] producerThreads = new Thread[numberOfProducers];
        Thread[] consumerThreads = new Thread[numberOfConsumers];

        for (int i = 0; i < numberOfProducers; i++) {
            producerThreads[i] = new Thread(() -> {
                for (int j = 0; j < itemsPerProducer; j++) {
                    producer.run();
                }
            });
            producerThreads[i].start();
        }

        for (int i = 0; i < numberOfConsumers; i++) {
            consumerThreads[i] = new Thread(() -> {
                for (int j = 0; j < itemsPerProducer; j++) {
                    consumer.run();
                }
            });
            consumerThreads[i].start();
        }

        for (Thread t : producerThreads) {
            t.join();
        }
        for (Thread t : consumerThreads) {
            t.join();
        }

        assertTrue(buffer.getBufferList().isEmpty(), "Buffer should be empty after all operations");
    }


    @Test
    @DisplayName("Test concurrent production and consumption")
    void testConcurrentProductionAndConsumption() throws InterruptedException {
        int numberOfOperations = 50;

        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < numberOfOperations; i++) {
                producer.run();
            }
        });

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < numberOfOperations; i++) {
                consumer.run();
            }
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        assertTrue(buffer.getBufferList().isEmpty(), "Buffer should be empty after concurrent production and consumption");
    }



    @Test
    @DisplayName("Test InterruptedException in remove method")
    void testInterruptedExceptionOnRemove() throws InterruptedException {
        Thread removingThread = new Thread(() -> {
            try {
                consumer.run();
            } catch (NoSuchElementException e) {
                // Vi ignorerar detta exception eftersom att det är en förväntad bieffekt av att avbryta wait()
            }
        });

        removingThread.start();

        Thread.sleep(2000);

        removingThread.interrupt();

        removingThread.join();

        assertFalse(removingThread.isAlive(), "Thread should not be alive.");
    }


}