package main.java.blockchain.bidtracker;

import main.java.blockchain.bidtracker.models.Bid;
import main.java.blockchain.bidtracker.models.Item;
import main.java.blockchain.bidtracker.models.User;

import java.util.List;
import java.util.Set;

public interface BidTracker {

    /**
     *
     */
    public void recordBid(Item item, Bid bid) throws InvalidBidException;

    /**
     *
     * @param item
     * @return
     */
    public Bid getCurrentWinningBid(Item item);

    /**
     *
     * @param item
     * @return
     */
    public List<Bid> getBidsOnItem(Item item);

    /**
     *
     * @param user
     * @return
     */
    public Set<Item> getItemsWithBidFromUser(User user);

}
