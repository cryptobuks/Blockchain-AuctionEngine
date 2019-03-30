package main.java.blockchain.bidtracker;

import main.java.blockchain.bidtracker.models.Bid;
import main.java.blockchain.bidtracker.models.Item;
import main.java.blockchain.bidtracker.models.User;

import java.util.List;
import java.util.Set;

/**
 * Interface for a Bid Tracker that allows users to record bids on items and retrieve information on the state of the auction
 *
 */
public interface BidTracker {

    /**
     * Records a bid on the given item
     * @param item
     * @param bid
     * @throws InvalidBidException
     */
    public void recordBid(Item item, Bid bid) throws InvalidBidException;

    /**
     * Gets the highest bid on the given item
     *
     * @param item Item to retrieve highest bid on
     * @return Current highest bid
     */
    public Bid getCurrentWinningBid(Item item);

    /**
     * Gets all the bids on the given item
     *
     * @param item Item to retrieve bids from
     * @return List of bids on the item
     */
    public List<Bid> getBidsOnItem(Item item);

    /**
     * Gets all the items the given user bid on
     *
     * @param user User to retrive bid on items from
     * @return Set of items that user bid on
     */
    public Set<Item> getItemsWithBidFromUser(User user);

}
