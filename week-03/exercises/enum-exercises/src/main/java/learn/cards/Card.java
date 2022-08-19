package learn.cards;

public class Card {

    // 1. Add a Suit and Rank field to the Card class.

    private Suit cardSuit;
    private Rank cardRank;

    // 2. Add a constructor that accepts a Suit and Rank and sets the appropriate fields.
    public Card(Suit cardSuit, Rank cardRank){
        this.cardSuit = cardSuit;
        this.cardRank = cardRank;
    }
    // 3. Add getters for both suit and rank.

    public Rank getCardRank(){
        get cardRank;
    }

    public String getName() {

        // 4. Complete the getName method.
        // Given a card's suit and rank, getName returns a String in the format:
        // "[rank] of [suit]"

        // Examples:
        // Ace of Clubs
        // 5 of Diamonds
        // King of Hearts
        // 9 of Spades

        // Note: it's unlikely you'll be able to use the enum name directly since enum naming conventions
        // don't match the required output.
        return null;
    }
}
