package org.arzije.ziberovska.mockedObjects;

import org.arzije.ziberovska.Buffer;
import org.arzije.ziberovska.Item;

import java.util.LinkedList;
/*

public class BufferMockHelper extends Buffer {

    private final boolean simulateFull;
    private final boolean simulateEmpty;

    public BufferMockHelper(boolean simulateFull, boolean simulateEmpty) {
        this.simulateFull = simulateFull;
        this.simulateEmpty = simulateEmpty;
        this.buffer = new LinkedList<>();
    }


    @Override
    public synchronized boolean add(Item item) {
        if (simulateFull) {
            return false;
        }
        return super.add(item);
    }

    @Override
    public synchronized Item remove() {
        if (simulateEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return null;
        }
        return super.remove();
    }

    // Eventuellt lägga till ytterligare metoder för att manipulera buffertens tillstånd eller beteende
}
*/

