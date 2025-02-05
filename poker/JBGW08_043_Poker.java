import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JBGW08_043_Poker {
    //포커 게임 진행 순서
    // 1. 플레이어 수를 입력받는다
    // 2. 사용자 이름을 입력받고 나머지는 임의
    //      먼저 카드 객체 52장을 만들어서 덱에 저장한다
    // 3. 총 52장의 카드를 플레이어들에게 5장 씩 나눠준다
    // 4. 플레이어들의 카드 패의 순위를 매긴다
    // 5. 각 플레이어들의 순위와 1등 플레이어 이름 출력

    private int players;        //플레이어 수
    private List<Player> playerList;    //플레이어들 리스트     0번:사용자
    private List<Card> deck;    //플레이들에게 5장씩 나눠줄 카드 52장이 있는 덱
    Random random = new Random();

    //이름 리스트에 미리 정해둔 배열의 이름들을 랜덤하게 넣음
    private void addRandomPlayers(int num){
        String[] otherNames = { //최소 9개
            "승주", "의석", "영호", "수아", "민경", "동수", "지호", "짱구", "맹구"
        };

        List<String> otherNamesList = new ArrayList<>(Arrays.asList(otherNames));
        for(int i=0;i<num;i++){
            //리스트에서 랜덤하게 한 이름을 뽑고 플레이어 객체를 리스트에 추가
            int index = random.nextInt(otherNamesList.size());
            playerList.add(new Player(otherNamesList.get(index)));
            otherNamesList.remove(index);
        }

        //테스트 출력
        //playerList.forEach((x)->System.out.print(x + ", "));
    }
    
    //덱에 모든 52장의 새 카드 객체 저장
    private void fillNewDeck(){
        deck = new ArrayList<Card>();
        for(Rank r : Rank.values()){
            for(Suit s : Suit.values()){
                deck.add(new Card(s, r));
            }
        }
    }

    //모든 플레이어의 모든 카드 출력
    void printPlayersDeck(){
        for(Player p : playerList){
            System.out.println(p.getName() + "의 카드 ----------------");
            for(Card c : p.deck){
                System.out.printf("%-13s", c);
            }
            System.out.println();
        }
    }


    //전체 게임 함수
    public void game() {
        Scanner scanner = new Scanner(System.in);

        //플레이어 수를 입력받기
        while(true) {
            System.out.print("플레이어 수를 입력(2~10): ");
            try{
                players = scanner.nextInt();

            }
            catch(Exception e){
                System.out.println("잘못된 입력입니다.");
                scanner.nextLine();
                continue;
            }
            if(players < 2 || players > 10){
                System.out.println("범위에서 벗어남");
                continue;
            }
            break;
        }
        scanner.nextLine();


        //사용자 이름을 입력받기
        playerList = new ArrayList<>();

        while(true){
            System.out.print("사용자의 이름을 입력: ");
            try{
                playerList.add(new Player(scanner.nextLine()));

            }
            catch(Exception e){
                System.out.print("잘못된 입력입니다.");
                continue;
            }
            break;
        }

        //나머지 플레이어들의 이름을 임의로 정하기
        addRandomPlayers(players - 1);

        //모든 카드 있는 덱 준비
        fillNewDeck();

    
        //플레이어들에게 덱에서 카드 5장씩 나눠주기
        for(int i=0; i<players*5; i++){
            int randomIndex = random.nextInt(deck.size());
            playerList.get(i%players).giveCard(deck.get(randomIndex));
            deck.remove(randomIndex);
        }

        //각각 카드 패 조합 결정하기
        playerList.forEach((Player p) -> {
            HandInspector inspector = new HandInspector(p);
            inspector.inspection();
        });

        //플레이어들의 패 힘 순으로 정렬되어있음
        printPlayersDeck();


        //각 플레이어들의 순위를 비교해서 1등 찾기
        //player comparable로 했으므로 정렬 가능
        Collections.sort(playerList);
        //이제 오름차순으로 플레이어가 정렬돼있음


        //각 플레이어들의 조합과 순위 발표
        System.out.println("\n플레이어들의 순위-----------");

        for(int i=0;i<players;i++){
            Player p = playerList.get(i);
            System.out.printf(
                "%2d등 %s %s ",
                (players - i), p.getName(), p.handRanking.name() );

            switch(p.handRanking){
                case HighCard:
                case OnePair:
                case ThreeOfaKind:
                case Straight:
                case FourOfaKind:
                System.out.println(p.compareCard.rank);
                break;

                case TwoPairs:
                case FullHouse:
                System.out.println(p.compareCard.rank + " " +
                    p.compareCard2.rank);
                break;

                case Flush:
                System.out.println(p.compareCard.suit);
                break;

                case StraightFlush:
                System.out.println(p.compareCard);
                break;
            }

        }

        //종료
        scanner.close();
    }

    public static void main(String[] args) {
        JBGW08_043_Poker poker = new JBGW08_043_Poker();
        poker.game();
    }
}
