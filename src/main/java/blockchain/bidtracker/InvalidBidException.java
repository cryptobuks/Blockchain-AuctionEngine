package main.java.blockchain.bidtracker;

/**
 * Exception thrown when a user makes an invalid bid (bid too low, nonexistent item, etc.)
 * Reason for invalid bid should be included in the constructor
 */
public class InvalidBidException extends Exception{
    public InvalidBidException(String message) {
        super(message);
    }
}
