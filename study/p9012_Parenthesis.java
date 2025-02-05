import java.util.Scanner;
import java.util.Stack;

class p9012_Parenthesis{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();   //String입력을 받을 반복 횟수
        sc.nextLine();
        
        String[] strings = new String[count];
        for(int i=0;i<count;i++){
            strings[i] = sc.nextLine(); //Strings 입력 받음
        }
        

        for(int i=0;i<count;i++){
            char[] chars = strings[i].toCharArray();    //한 라인을 char배열로 변환
            Stack<Character> st = new Stack<>();        //계산에 이용할 스택
            String result = "";                         //최종 출력할 답 (YES, NO)

            for(char c : chars){
            // '(' 일 경우 스택에 넣음
            if(c == '('){
                st.push(c);
            }
            // ')' 일 경우 스택에서 하나를 꺼내고, 스택이 비었으면 VPS가 아님
            else{
                if(!st.isEmpty()){
                    //스택에 내용물이 있다면 -> 하나 뺀다
                    st.pop();
                }
                else{
                    //스택에 아무것도 없다면 잘못됐다
                    result = "NO";
                    break;
                }
            }
               
            }

            //이미 NO라면 출력하고 끝냄
            if(result == "NO"){
                System.out.println(result);
                continue;
            }
            

            // 이번 문자열에서 모든 문자를 반복했는데
            // 스택에 '('가 남아있다면 VPS가 아니다.
            if(!st.isEmpty()){
                //스택에 내용물이 남아있으므로 틀렸음
                result = "NO";
            }
            else{
                //스택이 비었으므로 '(' 와 ')'의 갯수가 동일했다. -> VPS가 맞음
                result = "YES";
            }
        
            System.out.println(result);

        }


        sc.close();
    }
}