public class JBGW08_043_Maze {
    static int last_count = 0;
    static boolean clear = false;

    static int callCount = 0;

    static void maze(int x, int y, int[][] map, int[][] checked, int size, int count){
        System.out.println("method called: " + ++callCount);

        if(clear) return;
        if(x < 0 || x > size - 1 || y < 0 || y > size - 1) return;
        if(map[x][y] == 1) return;
        if(checked[x][y] == 1) return;

        last_count = last_count < count? count: last_count;
        checked[x][y] = 1;

        if(x == size - 1 && y == size - 1){
            System.out.println("Pass, " + count);
            clear = true;
            return;
        }

        maze(x + 1, y, map, checked, size, count+1);
        maze(x, y + 1, map, checked, size, count+1);
        maze(x - 1, y, map, checked, size, count+1);
        maze(x, y - 1, map, checked, size, count+1);
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

        maze(0, 0, maze, checked, size, 1);
        
        if(!clear) System.out.println("Fail, " + last_count);


        System.out.println("");
        System.out.println("----checked pass----");

        printArray(checked);
    }
}
