import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author hao
 * @create 2019-07-25 ${TIM}
 */
public class DemoTest {
    public static void main(String[] args) {
        Map<String,Object> map = new TreeMap<>();
        map.put("1","1");
        map.put("2","b");
        map.put("3","c");
        map.put("4","d");
        map.put("5","e");
        map.put("6","f");
        map.put("7","g");
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }

    }
}
