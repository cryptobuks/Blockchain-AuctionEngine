package main.java.blockchain.bidtracker;

import main.java.blockchain.bidtracker.models.Bid;
import main.java.blockchain.bidtracker.models.Item;
import main.java.blockchain.bidtracker.models.User;

import java.util.*;

public class AuctionTracker implements BidTracker {

    private Map<Item, List<Bid>> auctionMap;
    private Map<User, Set<Item>> usersItems;

    public AuctionTracker() {
        auctionMap = new HashMap<>();
        usersItems = new HashMap<>();
    }

    @Override
    public void recordBid(Item item, Bid bid) throws InvalidBidException {
        validateBid(item, bid);

        auctionMap.putIfAbsent(item, new ArrayList<>());
        auctionMap.get(item).add(bid);

        usersItems.putIfAbsent(bid.getUser(), new HashSet<>());
        usersItems.get(bid.getUser()).add(item);
    }

    @Override
    public Bid getCurrentWinningBid(Item item) {
        Objects.requireNonNull(item, "item must not be null");

        if (!auctionMap.containsKey(item)) {
            return null;
        } else {
            List<Bid> bids = auctionMap.get(item);
            return bids.get(bids.size() - 1);
        }
    }

    @Override
    public List<Bid> getBidsOnItem(Item item) {
        Objects.requireNonNull(item, "item must not be null");
        return auctionMap.getOrDefault(item, null);
    }

    @Override
    public Set<Item> getItemsWithBidFromUser(User user) {
        Objects.requireNonNull(user, "user must not be null");
        return new HashSet<>(usersItems.get(user));
    }

    public Map getAuctionMapCopy() {
        return new HashMap<>(auctionMap);
    }

    public Map getUsersItemsCopy() {
        return new HashMap<>(usersItems);
    }

    private void validateBid(Item item, Bid bid) throws InvalidBidException {
        Objects.requireNonNull(item, "item must not be null");
        Objects.requireNonNull(bid, "bid must not be null");

        List<Bid> bids = auctionMap.get(item);
        if (bids!=null && bid.getValue() <= bids.get(bids.size() - 1).getValue()) {
            throw new InvalidBidException("bid value must be higher than the current highest bid");
        }

    }
}
