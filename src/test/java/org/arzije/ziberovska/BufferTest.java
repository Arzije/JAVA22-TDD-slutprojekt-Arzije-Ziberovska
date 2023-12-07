package org.arzije.ziberovska;

import org.arzije.ziberovska.mockedObjects.MockBuffer;
import org.arzije.ziberovska.mockedObjects.MockItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BufferTest {

    private MockBuffer buffer;
    private MockItem item;
    @BeforeEach
    void setUp() {
        buffer = new MockBuffer();
    }

    @Test
    @DisplayName("Testing if add item in Buffer is added")
    void testBufferItemAdd() {
        item = new MockItem("TestItem");
        assertTrue(buffer.getBufferVariable().add(item), "Item added");
    }

    @Test
    @DisplayName("Testing if remove item from Buffer is removed")
    void testBufferItemRemove() {
        item = new MockItem("TestItem");
        buffer.add(item);
        assertEquals(item, buffer.getBufferVariable().remove());
    }


    @Test
    @DisplayName("Test if items are added correctly to the buffer")
    void testItemsAddedToBuffer() {
        buffer.add(new Item("Item1"));
        buffer.add(new Item("Item2"));
        assertEquals(2, buffer.getBufferSize(), "Buffer should contain 2 items");
    }

    @Test
    @DisplayName("Test if items are removed correctly from the buffer")
    void testItemsRemovedFromBuffer() {
        buffer.add(new Item("Item1"));
        buffer.add(new Item("Item2"));
        buffer.remove();

        assertEquals(1, buffer.getBufferSize(), "Buffer should contain one (1) item after removal");
    }

    @Test
    @DisplayName("Test isEmpty method on buffer")
    void testIsEmpty() {
        assertTrue(buffer.getBufferVariable().isEmpty(), "Buffer should be empty initially");

        buffer.add(new MockItem("TestItem"));
        assertFalse(buffer.getBufferVariable().isEmpty(), "Buffer should not be empty after adding an item");

        buffer.remove();
        assertTrue(buffer.getBufferVariable().isEmpty(), "Buffer should be empty after removing the item");
    }

    @Test
    @DisplayName("Test NullPointerException item with null id")
    public void testItemWithNullId() {
        assertThrows(NullPointerException.class, () -> new MockItem(null));
    }


    @Test
    @DisplayName("Testing thread safety of add and remove methods")
    void testThreadSafety() throws InterruptedException {
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
        assertTrue(buffer.getBufferVariable().isEmpty(), "Buffer should be empty after all add and remove operations");
    }

    @Test
    @DisplayName("Testing Buffer with multiple producers and consumers")
    void testBufferWithMultipleThreads() throws InterruptedException {
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

        assertTrue(buffer.getBufferVariable().isEmpty(), "Buffer should be empty after all operations");
    }

    @Test
    @DisplayName("Ensure buffer handles concurrent access")
    void testConcurrentAccess() throws InterruptedException {
        Runnable addTask = () -> {
            for (int i = 0; i < 100; i++) {
                buffer.add(new MockItem("Item " + i));
            }
        };

        Thread thread1 = new Thread(addTask);
        Thread thread2 = new Thread(addTask);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        assertEquals(200, buffer.getBufferSize(), "Buffer should contain all items added by both threads");
    }

    /**
     * Tests the behavior of Buffer's remove method when interrupted during waiting.
     * This test initiates a thread to perform remove on an empty buffer, causing it to wait.
     * The thread is then interrupted, leading to an InterruptedException inside remove method.
     * The test validates that the thread terminates correctly after the interruption,
     * despite a NoSuchElementException caused by attempting to continue execution post-interruption.
     */
    @Test
    @DisplayName("Test InterruptedException in remove method")
    void testInterruptedExceptionOnRemove() throws InterruptedException {
        Thread removingThread = new Thread(buffer::remove);

        removingThread.start();

        Thread.sleep(2000);

        removingThread.interrupt();

        removingThread.join();

        assertFalse(removingThread.isAlive(), "Thread should ot be alive.");
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


}