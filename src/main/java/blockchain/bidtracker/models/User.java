package main.java.blockchain.bidtracker.models;

import java.util.Objects;

/**
 * Represents an auction user
 */
public class User {
    private final String id;
    private final String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof User) {
            User user = (User) o;
            return Objects.equals(id, user.id) && Objects.equals(name, user.name);
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
