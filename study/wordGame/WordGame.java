import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class word implements Comparable<word>{
    private String key;
    private String value;
    

    public word(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(word o) {
        return key.compareTo(o.key);
    }

    
}

class WordGame{
    private ArrayList<word> list;
    private Random random;

    public WordGame() {
        this.list = new ArrayList<>();
        this.random = new Random();
        getWords();
    }

    public void getWords(){
        File file = new File("words.txt");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {

            String line = "";
            while((line = reader.readLine()) != null) {

                // 공백이 아닌 탭으로 나뉘어져 있는 라인들 처리
                if(line.contains("\t")){
                    line = line.replace("\t", " ");
                }

                String[] wordInfo = line.split(" ");

                // 뜻이 두개 이상 -> 한 문장으로 합칠 것임
                String meaning = "";
                for(int i=2; i<wordInfo.length; i++){
                    meaning += wordInfo[i].trim() + " ";
                }
                list.add(new word(wordInfo[1], meaning.trim()));
                
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public word getRandomWord(){
        return list.get(random.nextInt(list.size()));
    }

    public void game(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----영단어 맞추기 게임-----");

        int count = 1;
        while(true){
            System.out.print("맞출 단어 개수 입력: ");
            count = scanner.nextInt();
            if(count < 1 || count > list.size()){
                System.out.println("잘못된 입력입니다.");
                continue;
            }
            break;
        }


        scanner.nextLine();

        int score = 0;

        for(int i=0; i<count; i++){
            System.out.println((i + 1) + "번 문제   현재 스코어:" + score);

            //w = 1000개중에서 랜덤으로 뽑은 단어 객체 하나
            word w = this.getRandomWord();
            
            // 나온 단어는 제거
            list.remove(w);

            System.out.println("뜻: " + w.getValue());
            System.out.print("단어: ");

            String input = "";

            while(true){
                input = scanner.nextLine().trim();
                if(input.isEmpty()){
                    System.out.print("단어를 입력하세요: ");
                    continue;
                }
                break;
            }

            if(input.equals(w.getKey())){
                // 맞았다!
                score++;
                System.out.println("정답입니다!\n");
            }
            else{
                // 틀렸다
                System.out.println("틀렸습니다!  정답:" + w.getKey());
                System.out.println();
            }

        }

        System.out.println("게임 종료\n맞힌 갯수:" 
        + score + "/" + count + " (" 
        + ((int)((double)score/(double)count * 100)) + "%)");

        scanner.close();
    }


    public static void main(String[] args) {
        WordGame Game = new WordGame();
        Game.game();
    }
}