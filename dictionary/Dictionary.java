import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Scanner;

class Dictionary{
    private Hashtable<String, String> table;

    public Dictionary() {
        this.table = new Hashtable<>();
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
                table.put(wordInfo[1], meaning.trim());
                
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // 영어 단어를 입력받으면 해시테이블에서 검색 후 뜻을 반환
    // 테이블에 존재하지 않으면 null 반환
    public String searchMeaning(String word){
        return table.get(word);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();

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