package ca.mcgill.solitaire.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a deck of 52 cards with pre-determined winnable orders.
 */
public class Deck {
        private CardStack aCards;
        private int aConfiguration = 0;

        public Deck() {
                this(0);
        }

        public Deck(int pConfiguration) {
                aConfiguration = pConfiguration;
                buildDeck();
        }

        public void buildDeck() {
                List<Card> cards = new ArrayList<>();
                for (Suit suit : Suit.values()) {
                        for (Rank rank : Rank.values()) {
                                cards.add(Card.get(rank, suit));
                        }
                }

                // Loại bỏ xáo bài ngẫu nhiên (Collections.shuffle)
                // Sắp xếp bài cố định để tạo bộ bài thắng được:
                if (aConfiguration == 1) {
                        Collections.reverse(cards);
                } else if (aConfiguration == 2) {
                        List<Card> customOrder = new ArrayList<>();
                        for (int i = 0; i < cards.size(); i += 2) {
                                customOrder.add(cards.get(i));
                        }
                        for (int i = 1; i < cards.size(); i += 2) {
                                customOrder.add(cards.get(i));
                        }
                        cards = customOrder;
                }

                aCards = new CardStack(cards);
        }

        public void shuffle() {
                buildDeck();
        }

        public void push(Card pCard) {
                assert pCard != null;
                aCards.push(pCard);
        }

        public Card draw() {
                assert !isEmpty();
                return aCards.pop();
        }

        public boolean isEmpty() {
                return aCards.isEmpty();
        }
}
