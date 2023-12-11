package org.arzije.ziberovska.mockedObjects;

import org.arzije.ziberovska.Buffer;
import org.arzije.ziberovska.Item;

import java.util.Queue;

public class MockBuffer extends Buffer {

    public MockBuffer(){

    }

    public int getBufferSize(){
        return super.buffer.size();
    }

    public Queue<Item> getBufferList(){
        return super.buffer;
    }
}
