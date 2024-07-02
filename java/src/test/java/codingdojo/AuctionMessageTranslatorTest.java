package codingdojo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuctionMessageTranslatorTest {

    private AuctionEventListener mockListener;
    private AuctionMessageTranslator translator;

    @BeforeEach
    void setUp() {
        mockListener = mock(AuctionEventListener.class);
        translator = new AuctionMessageTranslator(mockListener);
    }


    @Test
    void notifiesAuctionClosedWhenCloseMessageReceived() {
        String message = "SOLVersion: 1.1; Event: CLOSE;";
        translator.processMessage(message);
        verify(mockListener).auctionClosed();
    }

    @Test
    public void notifiesBidDetailsWhenPriceMessageReceived() {
        String message =
                "SOLVersion: 1.1; Event: PRICE; CurrentPrice: 192; Increment: 7; Bidder: Someone else;";
        translator.processMessage(message);
        verify(mockListener).currentPrice(192);
    }
}

