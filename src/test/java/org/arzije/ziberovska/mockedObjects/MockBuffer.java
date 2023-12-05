package org.arzije.ziberovska.mockedObjects;

import org.arzije.ziberovska.Buffer;

public class MockBuffer extends Buffer {

    public int getBufferSize(){
        return buffer.size();
    }
}
