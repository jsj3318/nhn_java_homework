import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JBGW08_043_AST {
    private Node root;
    List<Integer> nums;
    List<String> operators;
    Node[] nodes;

    public JBGW08_043_AST(){
        root = null;
        nums = new ArrayList<>();
        operators = new ArrayList<>();
    }

    public Node getRoot() {
        return root;
    }

    private void updateNodes(Node target, Node newNode){
        for(int i=0; i<nodes.length; i++){
            if(nodes[i] == target){
                nodes[i] = newNode;
            }
        }
    }

    public Node generateAST(String expression){
        // 들어온 수식을 트리로 만들고 루트 반환
        root = null;
        nums.clear();
        operators.clear();

        String[] items = expression.trim().split(" ");
        

        // 숫자와 연산자를 분리
        for(int i=0; i< items.length; i++){
            if(i % 2 == 0){
                // 숫자
                nums.add(Integer.parseInt(items[i]));
            }
            else{
                // 연산자
                operators.add(items[i]);
            }
        }

        nodes = new Node[operators.size()];

        // 연산자를 기준으로 계산
        // 먼저 곱셈과 나눗셈을 계산
        // 우선순위 가장 우선인 곱셈과 나눗셈은 앞에서부터 읽을 때 가장 우선순위 빠른 애부터 오기 떄문에
        // right에 연산자 노드가 올 일이 없음
        for(int i=0; i<operators.size(); i++){
            if( operators.get(i).equals("*")||
                operators.get(i).equals("/")){

                    Node newNode = new Node(operators.get(i));

                    //left
                    if(i == 0 || nodes[i - 1] == null ){
                        newNode.left = new Node(nums.get(i));
                    }
                    else{
                        newNode.left = nodes[i - 1];
                        updateNodes(nodes[i - 1], newNode);
                    }
                    
                    //right
                    newNode.right = new Node(nums.get(i+1));
                    
                    //루트가 되고 nodes에 저장
                    root = newNode;
                    nodes[i] = newNode;

                }
        }
        //그 뒤 덧셈과 뺄셈 계산
        // 덧셈 뺄셈 노드는 left와 right에 연산자 노드가 올 수 있음
        for(int i=0; i<operators.size(); i++){
            if( operators.get(i).equals("+") ||
                operators.get(i).equals("-")){

                    Node newNode = new Node(operators.get(i));

                    //left
                    if(i == 0 ){
                        newNode.left = new Node(nums.get(i));
                    }
                    else{
                        newNode.left = nodes[i - 1];
                        updateNodes(nodes[i - 1], newNode);
                    }
                    
                    //right
                    if(i == (operators.size() - 1) || nodes[i + 1] == null){
                        newNode.right = new Node(nums.get(i + 1));
                    }
                    else{
                        newNode.right = nodes[i + 1];
                        updateNodes(nodes[i + 1], newNode);
                    }
                    
                    //루트가 되고 nodes에 저장
                    root = newNode;
                    nodes[i] = newNode;

                }
        }


        return root;
    }

    public int evaluation(Node ast){
        if(ast.isNum){
            //노드가 끝의 숫자 노드면 숫자 반환
            return ast.num;
        }

        //노드가 연산자 노드일 경우 left와 right를 해당 연산자로 계산한 값을 반환
        switch(ast.operator.toCharArray()[0]){
            case '+':
            return evaluation(ast.left) + evaluation(ast.right);
            case '-':
            return evaluation(ast.left) - evaluation(ast.right);
            case '*':
            return evaluation(ast.left) * evaluation(ast.right);
            case '/':       default:
            return evaluation(ast.left) / evaluation(ast.right);
        }

    
    }


    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isValid(String string){
        String[] items = string.trim().split(" ");

        //내용물이 짝수인 경우
        if(items.length % 2 == 0){
            return false;
        }

        for(int i=0;i<items.length;i++){

            if(i%2 == 0){
                //숫자 부분
                if(isNumeric(items[i]) == false){
                    //숫자가 아님
                    return false;
                }
            
            }
            else{
                //연산자 부분
                if( items[i].equals("+")
                 || items[i].equals("-")
                 || items[i].equals("*")
                 || items[i].equals("/") ){
                    continue;
                 }
                 else{
                    return false;
                 }
            }

        }
        return true;

    }

    public void calculator(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("> ");
            String expression = scanner.nextLine();

            //exit() => 종료
            if(expression.equals("exit()")){
                break;
            }

            //입력된 수식이 올바른지 검사
            if(isValid(expression) == false){
                scanner.close();
                throw new RuntimeException("InvalidExpressionException");
            }
    
            // 여기 주석 서로 바꿀 경우 (((1),+,(((2),*,(3)),/,(4))),-,(5)) 이런 식으로 출력해서 보여 줌 -------------------------------------------------------
            System.out.println("(" + generateAST(expression) + ")");
            //generateAST(expression);

            System.out.println(evaluation(root));

        }    

        scanner.close();
    }

    public static void main(String[] args) {
        JBGW08_043_AST ast = new JBGW08_043_AST();
        ast.calculator();
    }
}