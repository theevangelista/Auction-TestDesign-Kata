package codingdojo;


import java.util.HashMap;

public class AuctionMessageTranslator {
    private final AuctionEventListener listener;

    public AuctionMessageTranslator(AuctionEventListener listener) {
        this.listener = listener;
    }

    public void processMessage(String message) {

        if (message.contains("CLOSE")) {
            listener.auctionClosed();
        } else if (message.contains("PRICE")) {
            var data = new HashMap<String, String>();
            for (var element : message.split(";")) {
                var pair = element.split(":");
                data.put(pair[0].trim(), pair[1].trim());
            }
            var currentPrice = Integer.parseInt(data.get("CurrentPrice"));
            var increment = Integer.parseInt(data.get("Increment"));
            var bidder = data.get("Bidder");

            listener.currentPrice(currentPrice);
        } else {
            listener.auctionFailed();
        }
    }
}
