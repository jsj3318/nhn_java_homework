public class Node {
    String operator;
    int num;
    Node left;
    Node right;
    boolean isNum;

    Node(String operator) {
        left = right = null;
        isNum = false;
        this.operator = operator;
    }
    Node(int num){
        left = right = null;
        isNum = true;
        this.num = num;
    }

    @Override
    public String toString() {
        if(isNum){
            return String.valueOf(num);
        }
        else{
            return "(" + left + ")," + operator + ",(" + right + ")";
        }

    }
}
