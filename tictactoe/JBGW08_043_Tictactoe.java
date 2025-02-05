import java.util.Random;
import java.util.Scanner;

/*
 * 플레이어 턴
 * 둘 x y 입력 받음
 * 둘 수 있는 곳인지 검사
 *  플레이어 돌을 둠 : 재입력 시킴
 * 
 * 한 줄 완성 -> 플레이어 승리
 * 꽉참 -> 무승부
 * 
 * 컴퓨터 턴
 * 남은 칸 중에서 랜덤하게 둠
 * 헌 줄 완성 -> 컴퓨터 승리
 * 
 */

public class JBGW08_043_Tictactoe {
    Scanner scanner;
    Random random;

    int[][] board = new int[3][3];
    int blank = 9;
    int turn = 0;

    JBGW08_043_Tictactoe(){ //생성자에서 스캐너 초기화
        scanner = new Scanner(System.in);
        random = new Random();
    }

    void printBoard(){  //보드 판 현재 상황 그리기
/*
 *       y
 *     0 1 2 
 *   0  | | 
 *     -----
 * x 1  | |
 *     -----
 *   2  | |
 * 
 * 
 */

        System.out.println("");
        System.out.println("      y");
        System.out.println("    0 1 2");

        for(int i=0; i<3; i++){

            if(i==1)    System.out.print("x " + i + " ");
            else        System.out.print("  " + i + " ");

            for(int j=0; j<3;j++){

                switch (board[i][j]) {    //칸 안에 표기
                    case 1: //플레이어
                        System.out.print("O");
                        break;

                    case 2: //컴퓨터
                        System.out.print("X");
                        break;

                    default://빈 칸
                        System.out.print(" ");
                        break;
                }

                //칸 경계선 표기
                if(j<2) System.out.print("|");
            }
            if(i < 2) System.out.print("\n    -----\n");
        }
        System.out.println("");
    }

    void playerInput(){ //플레이어 돌 두기   
        while(true){    //성공 할 때까지 반복
            System.out.print("돌을 둘 위치 입력(x, y): ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            //scanner.nextLine();

            //둘 수 있는 칸인지 검사
            
            if( x<0 || x>2 || y<0 || y>2 ){
                //범위에서 벗어난 입력
                System.out.println("0~2 값만 입력할 수 있습니다!");
            }
            else if(board[x][y] != 0){
                //두려는 곳이 빈칸이 아님
                System.out.println("그 곳에는 둘 수 없습니다!");
            }
            else{
                //둘 수 있음
                board[x][y] = 1;
                blank--;
                return;
            }

        }
        
    }

    boolean checkWin(int target){   //target 1:플레이어 2:컴퓨터
        //가로로 한 줄 만드는 경우
        for(int i=0;i<3;i++){
            if( board[i][0] == target &&
                board[i][1] == target &&
                board[i][2] == target)
                return true;
        }

        //세로로 한 줄 만드는 경우
        for(int i=0;i<3;i++){
            if( board[0][i] == target &&
                board[1][i] == target &&
                board[2][i] == target)
                return true;
        }
        
        //대각선 만드는 경우
        //  '\' 이 대각선
        if( board[0][0] == target &&
            board[1][0] == target &&
            board[2][0] == target)
                return true;

        //  '/' 이 대각선
        if( board[0][2] == target &&
            board[1][1] == target &&
            board[2][0] == target)
                return true;
        //해당 없음
        return false;
    }

    void cpInput(){
        //빈 칸 중에서 몇번째 빈 칸에 둘지 랜덤 함수로 정함
        int count = 0;
        int inputIndex = random.nextInt(blank); //0 ~ (blank-1) 난수


        for(int x=0; x<3; x++){
            for(int y=0; y<3; y++){
                if(board[x][y] == 0){
                    if(count++ == inputIndex){
                        //여기에 둘 것임
                        board[x][y] = 2;
                        blank--;
                        return;
                    }
                }
            }
        }
    }


    void gameStart(){   //게임 전체 함수

        while(true){    //게임 전체 반복

            //보드판 출력
            printBoard();

            switch(turn){   //턴에 따른 처리    0플레이어(기본값)   1컴퓨터
                case 0: //플레이어 차례------------
                    //플레이어에게 입력 받고 돌 두기
                    playerInput();

                    //게임이 끝나는지 검사
                    //플레이어 승리한 경우
                    if(checkWin(1)){
                        printBoard();
                        System.out.println("플레이어 승리!");
                        return;
                    }

                    //무승부 (플레이어 턴에서만 무승부 나옴)
                    if(blank == 0){
                        printBoard();
                        System.out.println("무승부!");
                        return;
                    }

                    turn = 1;   //  컴퓨터 턴으로 넘김

                break;

                case 1://컴퓨터 차례----------
                    //컴퓨터 돌 둠
                    cpInput();

                    //컴퓨터 승리한 경우
                    if(checkWin(2)){
                        printBoard();
                        System.out.println("컴퓨터 승리!!!");
                        return;
                    }
                    
                    turn = 0;   //  플레이어 턴으로 넘김

                break;
            }
            

        }

    }

    public static void main(String[] args){
        
        JBGW08_043_Tictactoe tictactoe = new JBGW08_043_Tictactoe();
        tictactoe.gameStart();

        System.out.println("");

    }

}
