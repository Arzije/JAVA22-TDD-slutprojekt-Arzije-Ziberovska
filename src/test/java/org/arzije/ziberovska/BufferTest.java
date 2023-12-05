package org.arzije.ziberovska;

import org.arzije.ziberovska.mockedObjects.MockItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BufferTest {

    @Test
    @DisplayName("Testing if add item in Buffer is added")
    void addItem(){
        Buffer buffer = new Buffer();
        Item item = new Item("Item");
        assertTrue(buffer.add(item));
    }

    @Test
    @DisplayName("Testing if remove item from Buffer is removed")
    void removeItem(){
        Buffer buffer = new Buffer();
        Item item = new Item("Item");
        buffer.add(item);
        assertEquals(item, buffer.remove());
    }

    @Test
    @DisplayName("Testing thread safety of add and remove methods")
    void testThreadSafety() throws InterruptedException {
        Buffer buffer = new Buffer();
        int numberOfItems = 10; // Antal objekt att lägga till/ta bort

        // Skapar en tråd för att lägga till objekt
        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < numberOfItems; i++) {
                buffer.add(new Item("Item " + i));
            }
        });

        // Skapar en tråd för att ta bort objekt
        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < numberOfItems; i++) {
                buffer.remove();
            }
        });

        // Startar trådarna
        producerThread.start();
        consumerThread.start();

        // Väntar på att båda trådarna ska avsluta
        producerThread.join();
        consumerThread.join();

        // Verifiera buffertens tillstånd
        assertTrue(buffer.buffer.isEmpty(), "Buffer should be empty after all add and remove operations");
    }


}