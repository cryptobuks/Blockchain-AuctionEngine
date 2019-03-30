package main.java.blockchain.bidtracker.models;

import java.util.Objects;

/**
 * Represents an item that can be bid on in the auction
 */
public class Item {
    private final String id;
    private final String name;

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof Item) {
            Item item = (Item) o;
            return Objects.equals(id, item.id) && Objects.equals(name, item.name);
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "{ id: " + id + ", name: " + name + " }";
    }
}
