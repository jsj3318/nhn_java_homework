//플레이어를 받아와서 덱의 조합을 판별

import java.util.Arrays;

public class HandInspector {
    
    Player player;
    Card[] deck;
    HandRanking handRanking;
    Card compareCard;
    Card compareCard2;
    
    HandInspector(Player p){
        player = p;
        deck = p.deck;
    }

    boolean updateRanking(HandRanking newRanking){
        if(handRanking.compareTo(newRanking) > 0){
            return false;
        }
        handRanking = newRanking;
        return true;
    }

    void inspection(){
        //여기서 모두 구하고 p에게 저장해줌

        //덱이 파워 순으로 오름차순 정렬
        Arrays.sort(deck);
        
        //조합 판별하기

        handRanking = HandRanking.HighCard;

        //연속 숫자 카운터 (12345)
        int combo = 1;
        //같은 무늬 카운터
        int sameSuit = 1;
        //같은 숫자 카운터
        int sameRank = 1;
        //페어 갯수 11 33
        int pairs = 0;
        //트리츨 갯수
        int three = 0;

        for(int i=0;i<=3;i++){   // 3 3 3 5 5


            //현재 숫자와 다음 숫자가 같은 경우
            //원 페어 / 투 페어 / 트리플 / 풀 하우스 / 포 카드
            if(deck[i].rank == deck[i+1].rank){
                switch(++sameRank){
                    case 2: //숫자 같은 한쌍이 나옴

                        //트리플이 있었다면 풀 하우스
                        if(three > 0){
                            if(updateRanking(HandRanking.FullHouse)){
                                //32풀하우스이므로 두번째 비교 카드로 써야함
                                compareCard2 = deck[i+1];
                            }
                        }
                        //페어가 있었다면 투 페어
                        else if(pairs++ > 0){
                            if(updateRanking(HandRanking.TwoPairs)){
                                compareCard2 = compareCard;
                                compareCard = deck[i+1];
                            }
                        }
                        else{
                            //페어가 없었다면 원 페어
                            if(updateRanking(HandRanking.OnePair)){
                                compareCard = deck[i+1];
                            }

                        }


                    break;

                    case 3:
                    //같은 숫자 세개가 나옴
                    //페어가 한개였다면 트리플
                    if(pairs == 1){
                        if(updateRanking(HandRanking.ThreeOfaKind)){
                            compareCard = deck[i+1];
                        }
                    }
                    //페어가 두개 있었다면 풀하우스
                    if(pairs == 2){
                        if(updateRanking(HandRanking.FullHouse)){
                            compareCard = deck[i+1];
                        }
                    }
                    three++;

                    break;
                    case 4:
                    //같은 페어 네개 = 포카드
                    if(updateRanking(HandRanking.FourOfaKind)){
                        compareCard = deck[i+1];
                    }

                    break;
                }



            }
            //현재 숫자와 다음 숫자가 다른 경우
            else{
                sameRank = 1;

            }

            //현재 숫자와 다음 숫자가 연속된 경우
            //스트레이트
            if(deck[i].rank.compareTo(deck[i+1].rank) == -1){
                if(combo++ == 5){
                    // 5개 연속됨
                    if(updateRanking(HandRanking.Straight)){
                        compareCard = deck[4];
                    }
                }

            }

            //현재 모양과 다음 모양이 같은 경우
            //플러시, 스트레이트 플러시
            if(deck[i].suit == deck[i+1].suit){

                if(++sameSuit == 5){
                    //5개의 모양이 일치함

                    //스트레이트 인 경우 스트레이트 플러시
                    if(handRanking == HandRanking.Straight){
                        if(updateRanking(HandRanking.StraightFlush)){
                            compareCard = deck[4];
                        }
                    }
                    //아닌 경우 플러시
                    else{
                        if(updateRanking(HandRanking.Flush)){
                            compareCard = deck[4];
                        }
                    }

                }

            }




        }
        
        //검사 끝난 뒤에도 하이 카드 일 경우
        if(handRanking == HandRanking.HighCard){
            compareCard = deck[4];
        }

        //검사 끝냈으므로 플레이어에게 정보 돌려줌
        player.compareCard = compareCard;
        player.compareCard2 = compareCard2;
        player.handRanking = handRanking;


    }
}
