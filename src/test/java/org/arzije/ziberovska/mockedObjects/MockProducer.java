package org.arzije.ziberovska.mockedObjects;

import org.arzije.ziberovska.Producer;

public class MockProducer implements Producer{

    private boolean isRunning = false;

    @Override
    public void run() {
        isRunning = true;
    }

    @Override
    public void stopRunning() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
