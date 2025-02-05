import java.util.HashMap;

public class DataBeanMapFactory {
    private static HashMap<String, DataBean> map;

    public static void registerDataBean(DataBean d){
        if( map == null){
            map = new HashMap<>();
        }
        map.put(d.getClass().getName(), d);
    }

    public static DataBean getClass(String name){
        if(map.get(name).getCreationType().equals("ProtoType") ){
            // 꺼낼 객체가 프로토타입
            return map.get(name).clone();
        }
        
        // 싱글톤
        return map.get(name);
    }

}
