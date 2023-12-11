package org.arzije.ziberovska.mockedObjects;

import org.arzije.ziberovska.Consumer;

public class MockConsumer implements Consumer {
    private final MockBuffer buffer;

    public MockConsumer(MockBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.remove();
    }

    @Override
    public void stopRunning() {
    }
}
