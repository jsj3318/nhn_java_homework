public class Test {
    public static void main(String[] args) {
        try{
            Student s = new Student(1, "Celine");
            DataBeanMapFactory.registerDataBean(s);

            NumberGenerator n = new NumberGenerator();
            DataBeanMapFactory.registerDataBean(n);

            DataBean s1 = DataBeanMapFactory.getClass("Student");
            DataBean s2 = DataBeanMapFactory.getClass("Student");
            DataBean n1 = DataBeanMapFactory.getClass("NumberGenerator");
            DataBean n2 = DataBeanMapFactory.getClass("NumberGenerator");
            
            System.out.println(s);
            System.out.println(s1);
            System.out.println(s2);
            
            System.out.println(n);
            System.out.println(n1);
            System.out.println(n2);
            
        }
        catch(Exception e){

        }
    }
}
