public class Main {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();


        
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(7);
        System.out.println(list.size());
        
        System.out.println(list.get(2));

        list.remove(2);

        System.out.println(list.get(2));
        System.out.println(list.size());


    }
}
