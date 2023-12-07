package org.arzije.ziberovska.mockedObjects;

import org.arzije.ziberovska.Item;

public class MockItem extends Item {

    public MockItem(String id) {
        super(id);
    }

    public String getId(){
        return super.id;
    }

}
