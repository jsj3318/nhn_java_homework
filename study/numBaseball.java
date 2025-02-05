import java.util.Scanner; //입력을 위한 헤더
import java.util.Random; //랜덤값 생성을 위한 헤더

public class numBaseball {
    public static void main(String[] args) {
        //기회, 정답, 입력 받는수, 스트라이크, 볼, 게임오버 여부 변수 만들기
        int life, ans1, ans2, ans3, ans4, num, num1, num2, num3, num4;
        int strike, ball;
        boolean gameover;
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        //전체 게임 반복
        while (true) {

            //게임오버 변수 초기화
            gameover = false;

            //기회 횟수 입력받기
            System.out.print("기회횟수를 입력하세요(1~100): ");
            life = sc.nextInt();
            while (life < 1 || life > 100) { //범위 밖의 수 입력 시 다시 입력받기
                System.out.print("1부터 100까지의 수를 입력하세요: ");
                life = sc.nextInt();
            }

            //랜덤답 만들기 (숫자 중복 체크)
            ans1 = rand.nextInt(10);
            do {
                ans2 = rand.nextInt(10);
            } while (ans1 == ans2);
            do {
                ans3 = rand.nextInt(10);
            } while (ans1 == ans3 || ans2 == ans3);
            do {
                ans4 = rand.nextInt(10);
            } while (ans1 == ans4 || ans2 == ans4 || ans3 == ans4);

            //답 맞추는 곳
            while (life > 0) {

                //수 입력받기
                System.out.println("\n남은 기회: " + life);
                System.out.print("네자리 수를 입력하세요(세자리 입력 시 첫번째 0): ");
                while (true) {
                    num = sc.nextInt();
                    if (num > 9999) {
                        System.out.print("네자리 수를 입력하세요: "); //다섯자리이상 입력 시 다시
                    } else if (num > 99) { //입력 받은 네수 자릿수마다 따로따로 저장
                        num1 = num / 1000;
                        num2 = (num % 1000) / 100;
                        num3 = (num % 100) / 10;
                        num4 = num % 10;

                        //입력 받은 수 중복확인
                        if (num1 == num2 || num1 == num3 || num1 == num4 || num2 == num3 || num2 == num4 || num3 == num4) {
                            System.out.print("중복되는 수가 있으니 다시 입력: ");
                            continue;
                        } else {
                            break;
                        }
                    } else {
                        System.out.print("네수를 입력하세요: "); //세자리이하 입력 시 다시
                    }
                }

                //볼, 스트라이크 변수 초기화
                ball = 0;
                strike = 0;

                //답과 입력받은 수 비교해서 볼, 스트라이크 계산
                if (num1 == ans1) strike++;
                else if (num1 == ans2 || num1 == ans3 || num1 == ans4) ball++;
                if (num2 == ans2) strike++;
                else if (num2 == ans1 || num2 == ans3 || num2 == ans4) ball++;
                if (num3 == ans3) strike++;
                else if (num3 == ans1 || num3 == ans2 || num3 == ans4) ball++;
                if (num4 == ans4) strike++;
                else if (num4 == ans1 || num4 == ans2 || num4 == ans3) ball++;

                //정답확인, 기회차감후 게임오버 확인
                if (strike == 4) gameover = true;
                if (--life == 0) gameover = true;

                //스트라이크, 볼, 아웃 출력
                if (strike > 0 && strike != 4) System.out.println(strike + "스트라이크");
                if (ball > 0) System.out.println(ball + "볼");
                if (strike == 0 && ball == 0) System.out.println("아웃!");

                //게임 끝났을 경우 다시시작 확인
                if (gameover) {
                    if (strike == 4) {
                        System.out.println("\n정답!!\n");
                    } else {
                        System.out.printf("\n게임오버!!  (정답:%d%d%d%d)\n\n", ans1, ans2, ans3, ans4);
                    }

                    System.out.print("(다시시작:1 종료:2) 입력: ");
                    int a;
                    while (true) {
                        a = sc.nextInt(); //숫자 입력받기
                        if (a == 1) { //1이면 지우고 다시시작
                            break;
                        } else if (a == 2) { //2이면 프로그램 종료
                            return;
                        } else {
                            System.out.print("1 또는 2 입력: ");
                        }
                    }
                }
                if (gameover) break;
            }
        }
    }
}
