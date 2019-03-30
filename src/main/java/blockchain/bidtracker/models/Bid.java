package main.java.blockchain.bidtracker.models;

import java.util.Objects;

/**
 * Represents a bid made in the auction
 */
public class Bid {
    private final User user;
    private final Item item;
    private final double value;

    public Bid(User user, Item item, double value) {
        this.user = user;
        this.item = item;
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public Item getItem() {
        return item;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
           return true;
        }

        if (o instanceof Bid) {
            Bid bid = (Bid) o;
            return Objects.equals(user, bid.user) && value == bid.value;
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, value);
    }

    @Override
    public String toString() {
        return "{ user: " + user + ", item: " + item + ", value: " + value + " }";
    }

}
