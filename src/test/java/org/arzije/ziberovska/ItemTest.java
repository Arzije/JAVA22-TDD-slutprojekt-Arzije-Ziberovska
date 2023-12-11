package org.arzije.ziberovska;

import org.arzije.ziberovska.mockedObjects.MockItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private MockItem item;

    @BeforeEach
    void setUp() {
        item = new MockItem("TestItem");
    }

    @Test
    @DisplayName("Test Constructor with valid id and test lowercase method")
    void testConstructorWithValidId() {
        assertEquals("testitem", item.getId(), "Constructor should set id correctly");
    }

    @Test
    @DisplayName("Constructor should correctly handle an empty string as ID")
    public void testConstructorWithEmptyString() {
        item = new MockItem("");
        assertEquals("", item.getId(), "ID should handle empty string correctly");
    }

    @Test   
    @DisplayName("Test item constructor with null id")
    void testItemWithNullId() {
        assertThrows(NullPointerException.class, () -> new MockItem(null), "Constructor should throw exception with null id");
    }

    @Test
    @DisplayName("Test item set ID with null id")
    public void testSetIdWithNull() {
        assertThrows(NullPointerException.class, () -> item.setId(null));
    }

    @Test
    @DisplayName("Test setting and getting item id")
    void testSetAndGetId() {
        item.setId("SomeId");
        assertEquals("someid", item.getId(), "Setting and getting id should work correctly");
    }

    @Test
    @DisplayName("Test that Set ID converts mixed case ID to lowercase")
    public void testSetIdWithMixedCase() {
        item.setId("miXeDCasE");
        assertEquals("mixedcase", item.getId(), "ID should be converted to lowercase");
    }

}
