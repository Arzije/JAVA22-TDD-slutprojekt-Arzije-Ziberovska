package org.arzije.ziberovska.mockedObjects;

import org.arzije.ziberovska.Producer;

public class MockProducer implements Producer{

    private boolean isRunning = false;

    @Override
    public void run() {
        // Implementera logik för att simulera producentens beteende
        isRunning = true;
    }

    @Override
    public void stopRunning() {
        // Implementera logik för att stoppa producenten
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
