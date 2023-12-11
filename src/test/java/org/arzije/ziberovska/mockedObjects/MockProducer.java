package org.arzije.ziberovska.mockedObjects;

import org.arzije.ziberovska.Producer;

public class MockProducer implements Producer{

    private final MockBuffer buffer;

    public MockProducer(MockBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.add(new MockItem("ProducedItem"));
    }

    @Override
    public void stopRunning() {

    }
}
