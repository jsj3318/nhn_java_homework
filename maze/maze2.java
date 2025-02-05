import java.util.LinkedList;
import java.util.Queue;


//큐를 이용해서 다시 짜본 코드

public class maze2 {

    public static class dot{
        public int x, y, count;
        dot(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    
    static void printArray(int[][] arr){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args){

        int size = 10;
        int maxMove = 0;

        int[][] maze = {
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 1, 1, 0},
            {1, 1, 0, 1, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
            {0, 1, 0, 1, 0, 0, 0, 1, 0, 0},
            {1, 1, 0, 0, 0, 1, 0, 0, 1, 0}
        };

        int[][] checked = new int[size][size];

        Queue<dot> que = new LinkedList<dot>();

        que.add(new dot(0, 0, 0));

        do{
            if(que.isEmpty()){
                System.out.println("Fail, " + maxMove);
                break;
            }

            dot d = que.poll();
            
            if(d.x < 0 || d.x > 9 || d.y < 0 || d.y > 9) continue;
            if(maze[d.x][d.y] == 1) continue;
            if(checked[d.x][d.y] == 1) continue;

            checked[d.x][d.y] = 1;
            maxMove = maxMove < d.count? d.count : maxMove;

            if(d.x == 9 && d.y == 9){
                System.out.println("Pass, " + d.count);
                break;
            }

            //인접 칸 큐에 추가
            que.add(new dot(d.x+1, d.y, d.count+1));
            que.add(new dot(d.x-1, d.y, d.count+1));
            que.add(new dot(d.x, d.y+1, d.count+1));
            que.add(new dot(d.x, d.y-1, d.count+1));
        }while(true);

        System.out.println("\n---checked map---");
        printArray(checked);


    }
}
