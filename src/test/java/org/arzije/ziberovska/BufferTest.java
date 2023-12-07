package org.arzije.ziberovska;

//import org.arzije.ziberovska.mockedObjects.BufferMockHelper;
import org.arzije.ziberovska.mockedObjects.MockBuffer;
import org.arzije.ziberovska.mockedObjects.MockItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class BufferTest {


    @Test
    @DisplayName("Testing if add item in Buffer is added")
    void testBufferItemAdd() {
        Buffer buffer = new Buffer();
        Item item = new Item("TestItem");
        assertTrue(buffer.add(item), "Item added");
    }

    @Test
    @DisplayName("Testing if remove item from Buffer is removed")
    void testBufferItemRemove() {
        Buffer buffer = new Buffer();
        Item item = new Item("TestItem");
        buffer.add(item);
        assertEquals(item, buffer.remove());
    }


    @Test
    @DisplayName("Test if items are added correctly to the buffer")
    void testItemsAddedToBuffer() {
        MockBuffer buffer = new MockBuffer();
        buffer.add(new Item("Item1"));
        buffer.add(new Item("Item2"));

        // Använd getBufferSize-metoden för att kontrollera buffertstorleken
        assertEquals(2, buffer.getBufferSize(), "Buffer should contain 2 items");
    }

    @Test
    @DisplayName("Test if items are removed correctly from the buffer")
    void testItemsRemovedFromBuffer() {
        MockBuffer buffer = new MockBuffer();
        buffer.add(new Item("Item1"));
        buffer.add(new Item("Item2"));
        buffer.remove();

        // Kontrollera att storleken på bufferten är 1 efter borttagning
        assertEquals(1, buffer.getBufferSize(), "Buffer should contain one (1) item after removal");
    }


/*
    @Test
    @DisplayName("Testing Buffer behavior when it is simulated as full")
    void testBufferWhenFull() {
        BufferMockHelper buffer = new BufferMockHelper(true, false);
        MockItem item = new MockItem("TestItem");
        assertFalse(buffer.add(item), "Buffer should not accept new items when simulated as full");
    }*/

//    @Test
//    @DisplayName("Testing Buffer behavior when it is simulated as empty")
//    void testBufferWhenEmpty() {
//        BufferMockHelper buffer = new BufferMockHelper(false, true);
//        assertNull(buffer.remove(), "Buffer should return null when simulated as empty");
//    }


//    @Test
//    @DisplayName("testBufferVariable")
//    void testBufferVariable() {
//        MockBuffer buffer = new MockBuffer();
//        assertInstanceOf(Queue.class, buffer.getBufferVariable());
//    }


    @Test
    @DisplayName("Testing thread safety of add and remove methods")
    void testThreadSafety() throws InterruptedException {
        Buffer buffer = new Buffer();
        int numberOfItems = 10;

        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < numberOfItems; i++) {
                buffer.add(new Item("Item " + i));
            }
        });

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < numberOfItems; i++) {
                buffer.remove();
            }
        });

        producerThread.start();
        consumerThread.start();

        // Väntar på att båda trådarna ska avsluta
        producerThread.join();
        consumerThread.join();

        // Verifiera buffertens tillstånd
        assertTrue(buffer.buffer.isEmpty(), "Buffer should be empty after all add and remove operations");
    }

    @Test
    @DisplayName("Testing Buffer with multiple producers and consumers")
    void testBufferWithMultipleThreads() throws InterruptedException {
        Buffer buffer = new Buffer();
        int numberOfProducers = 3;
        int numberOfConsumers = 3;
        int itemsPerProducer = 5;

        for (int i = 0; i < numberOfProducers; i++) {
            new Thread(() -> {
                for (int j = 0; j < itemsPerProducer; j++) {
                    buffer.add(new Item("Item " + j));
                }
            }).start();
        }

        for (int i = 0; i < numberOfConsumers; i++) {
            new Thread(() -> {
                for (int j = 0; j < itemsPerProducer; j++) {
                    buffer.remove();
                }
            }).start();
        }

        Thread.sleep(1000);

        assertTrue(buffer.buffer.isEmpty(), "Buffer should be empty after all operations");
    }

    @Test
    @DisplayName("Test InterruptedException in remove method")
    void testInterruptedExceptionOnRemove() throws InterruptedException {
        Buffer buffer = new Buffer();
        Thread removingThread = new Thread(buffer::remove);

        removingThread.start();

        Thread.sleep(2000);

        removingThread.interrupt();

        removingThread.join();

        assertFalse(removingThread.isAlive(), "Thread should be dead.");
    }

}