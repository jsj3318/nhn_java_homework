//플레이어 정보 클래스

public class Player implements Comparable<Player>{
    private String name;
    //카드 패 (카드 5개 배열)
    Card[] deck = new Card[5];
    int index = 0;
    HandRanking handRanking;    //덱의 조합
    Card compareCard;           //조합이 같을 때 비교할 카드
    Card compareCard2;          //조합이 같을 때 비교할 카드 2순위

    public void giveCard(Card c){
        deck[index++] = c;
    }

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Player p) {
        //플레이어 끼리 순위 비교
        //조합의 우열을 가리고 같을 경우 무승부 시키지 않고 카드로 비교
        if(this.handRanking != p.handRanking){
            //조합이 다르므로 조합으로 비교
            return this.handRanking.compareTo(p.handRanking);
        }

        //조합이 같은 경우 생각하기
        switch(this.handRanking){
            //비교용 카드 1번으로만 가리는 경우
            //하이카드, 원 페어, 트리플, 스트레이트, 플러쉬, 포카드, 스트레이트플러쉬
            case HighCard:
            case OnePair:
            case ThreeOfaKind:
            case Straight:
            case Flush:
            case StraightFlush:
                return this.compareCard.compareTo(p.compareCard);

            //비교 카드 두개로 비교하는 경우
            //투 페어, 풀하우스
            default:
            //비교카드 1의 숫자로 먼지 비교
            if(this.compareCard.rank != p.compareCard.rank){
                return this.compareCard.compareTo(p.compareCard);

            }
            //1의 숫자가 같으면 2의 숫자로 비교
            if(this.compareCard2.rank != p.compareCard2.rank){
                return this.compareCard2.compareTo(p.compareCard2);
            }
            //2의 숫자도 같으면 1 카드자체끼리 비교
            return this.compareCard.compareTo(p.compareCard);

            
        }


    }

    
}
