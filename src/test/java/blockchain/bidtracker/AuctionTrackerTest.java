package test.java.blockchain.bidtracker;

import main.java.blockchain.bidtracker.AuctionTracker;
import main.java.blockchain.bidtracker.InvalidBidException;
import main.java.blockchain.bidtracker.models.Bid;
import main.java.blockchain.bidtracker.models.Item;
import main.java.blockchain.bidtracker.models.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.*;

/**
 * Tests for AuctionTracker
 */

class AuctionTrackerTest {

    private AuctionTracker auctionTracker;

    private final User user1 = new User("user1", "Bob Ross");
    private final User user2 = new User("user2", "Kanye West");
    private final Item item1 = new Item("item1", "apple");
    private final Item item2 = new Item("item2", "banana");

    @BeforeEach
    void setUp() {
        auctionTracker = new AuctionTracker();
    }

    @Test
    public void recordBid_recordSingleBid() throws InvalidBidException {
        Bid bid = new Bid(user1, item1, 100.5);

        auctionTracker.recordBid(item1, bid);

        Map<Item, List<Bid>> copy = auctionTracker.getAuctionMapCopy();
        ;

        List<Bid> actualBid = copy.get(item1);
        List<Bid> expectedBid = new ArrayList<>();
        expectedBid.add(bid);

        assertEquals(expectedBid, actualBid);
    }

    @Test
    public void recordBid_recordSeveralBidsOnMultipleItem() throws InvalidBidException {
        auctionTracker.recordBid(item1, new Bid(user1, item1, 100.5));
        auctionTracker.recordBid(item1, new Bid(user2, item1, 200.5));
        auctionTracker.recordBid(item2, new Bid(user1, item2, 300.5));
        auctionTracker.recordBid(item2, new Bid(user2, item2, 400.5));


        Map<Item, List<Bid>> copy = auctionTracker.getAuctionMapCopy();
        List<Bid> actualBidsOnItem1 = copy.get(item1);
        List<Bid> actualBidsOnItem2 = copy.get(item2);

        List<Bid> expectedBidsOnItem1 = Arrays.asList(
                new Bid(user1, item1, 100.5),
                new Bid(user2, item1, 200.5));

        List<Bid> expectedBidsOnItem2 = Arrays.asList(
                new Bid(user1, item2, 300.5),
                new Bid(user2, item2, 400.5));

        assertEquals(expectedBidsOnItem1, actualBidsOnItem1);
        assertEquals(expectedBidsOnItem2, actualBidsOnItem2);
    }

    @Test
    public void recordBid_throwInvalidBidException_forInsufficientBid() throws InvalidBidException {
        auctionTracker.recordBid(item1, new Bid(user1, item1, 100.5));
        Bid insufficientBid = new Bid(user2, item1, 100);

        assertThrows(InvalidBidException.class, () -> {
            auctionTracker.recordBid(item1, insufficientBid);
        });
    }

    @Test
    public void recordBid_throwInvalidBidException_forEqualBid() throws InvalidBidException {
        auctionTracker.recordBid(item1, new Bid(user1, item1, 100.5));
        Bid equalBid = new Bid(user2, item1, 100.5);

        assertThrows(InvalidBidException.class, () -> {
            auctionTracker.recordBid(item1, equalBid);
        });
    }

    @Test
    public void getCurrentWinningBid_getsHighestBidFromMultipleBids() throws InvalidBidException {
        Bid bid1 = new Bid(user1, item1, 100.5);
        Bid bid2 = new Bid(user2, item1, 200.5);
        Bid bid3 = new Bid(user1, item1, 300.5);

        auctionTracker.recordBid(item1, bid1);
        auctionTracker.recordBid(item1, bid2);
        auctionTracker.recordBid(item1, bid3);

        double expectedHighestBidOnItem1 = bid3.getValue();

        Map<Item, List<Bid>> copy = auctionTracker.getAuctionMapCopy();
        List<Bid> actualBidsOnItem1 = copy.get(item1);
        double actualHighestBidOnItem1 = actualBidsOnItem1.get(actualBidsOnItem1.size() - 1).getValue();

        assertEquals(expectedHighestBidOnItem1, actualHighestBidOnItem1);
    }

}