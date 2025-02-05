public class Card implements Comparable<Card>{
    Suit suit;
    Rank rank;

    Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    
    @Override
    public String toString() {
        return "[" + rank + " " + suit + "]";
    }


    @Override
    public int compareTo(Card c) {
        //카드끼리 점수 비교
        //우선 수로 비교
        if(this.rank == c.rank){
            return this.suit.compareTo(c.suit);
        }
        
        return this.rank.compareTo(c.rank);
    }
    
}
