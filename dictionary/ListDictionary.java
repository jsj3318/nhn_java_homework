import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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

class ListDictionary{
    private ArrayList<word> list;

    public ListDictionary() {
        this.list = new ArrayList<>();
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

    // 영어 단어를 입력받으면 해시테이블에서 검색 후 뜻을 반환
    // 테이블에 존재하지 않으면 null 반환
    public String searchMeaning(String search){
        Collections.sort(list);
        //list는 영단어의 오름차 순으로 정렬 되어 있음

        int left = 0;
        int right = list.size() - 1;

        while(list.get(left).compareTo(list.get(right)) < 0 || list.get(left).compareTo(list.get(right)) == 0 ){
            int mid = left + (right - left) / 2;
            if(list.get(mid).getKey().compareTo(search) == 0){
                return list.get(mid).getValue();
            }
            else if(list.get(mid).getKey().compareTo(search) < 0){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListDictionary dictionary = new ListDictionary();

        System.out.println("[대학생이 알아야 할 1000가지 영단어 사전] (exit 또는 exit() 입력 시 종료)");

        while(true){
            System.out.print("검색할 영어 단어를 입력하세요: ");
            String search = scanner.nextLine();
    
            //exit() 입력시 프로그램 종료
            if(search.equals("exit()") || search.equals("exit")){
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            //사전에서 단어 검색 후 뜻 출력
            String meaning = dictionary.searchMeaning(search);
            if(meaning == null){
                System.out.println("사전에 존재하지 않는 단어입니다.");
            }
            else{
                System.out.println(search + " : " + meaning);
            }

            System.out.println("-------------------------------------------");

        }

        scanner.close();

    }
}