package org.arzije.ziberovska;

import org.arzije.ziberovska.Item;
import org.arzije.ziberovska.mockedObjects.MockItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItemTest {

    @Test
    @DisplayName("Test Constructor with valid id")
    void testConstructorWithValidId() {
        Item item = new Item("ValidId");
        assertEquals("validid", item.toString(), "Constructor should set id correctly");
    }

//    @Test
//    @DisplayName("testItemIdVariable??????")
//    void testItemIdVariable() {
//        MockItem item = mock(MockItem.class);
//
//        when(item.getId()).thenReturn("potatoe");
//
//        String result = item.getId();
//
//        verify(item).getId();
//        assertEquals("potatoe", result);
//        //assertInstanceOf(String.class, item.id);
//    }


//    @Test
//    @DisplayName("Test Constructor with null id")
//    void testConstructorWithNullId() {
//        Item item = new Item(null);
//        assertEquals("", item.toString(), "Constructor should handle null id");
//    }

    @Test
    @DisplayName("Test setId method")
    void testSetId() {
        Item item = new Item("initialId");
        item.setId("NewId");
        assertEquals("newid", item.toString(), "setId should update id correctly");
    }

//    @Test
//    @DisplayName("Test setId with null")
//    void testSetIdWithNull() {
//        Item item = new Item("initialId");
//        item.setId(null);
//        assertEquals("", item.toString(), "setId should handle null input");
//    }

}
