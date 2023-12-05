package org.arzije.ziberovska.mockedObjects;

import org.arzije.ziberovska.Buffer;
import org.arzije.ziberovska.Item;

import java.util.LinkedList;
import java.util.Queue;

public class BufferMockHelper extends Buffer {

    private boolean simulateFull;
    private boolean simulateEmpty;

    public BufferMockHelper(boolean simulateFull, boolean simulateEmpty) {
        this.simulateFull = simulateFull;
        this.simulateEmpty = simulateEmpty;
        this.buffer = new LinkedList<>(); // Använd en egen instans av bufferten
    }

    @Override
    public synchronized boolean add(Item item) {
        if (simulateFull) {
            return false; // Simulerar att bufferten är full
        }
        return super.add(item);
    }

    @Override
    public synchronized Item remove() {
        if (simulateEmpty) {
            try {
                wait(); // Simulerar att bufferten är tom och väntar
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Hanterar avbrott på rätt sätt
            }
            return null;
        }
        return super.remove();
    }

    // Eventuellt lägga till ytterligare metoder för att manipulera buffertens tillstånd eller beteende
}


