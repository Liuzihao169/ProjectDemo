import entity.Result;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author hao
 * @create 2019-07-12 ${TIM}
 */
public class DemoTest {
    @Test
    public void test(){
        String s = new String("abc");
        String s2 = s.intern();
        System.out.println(s2==s);
    }

    @Test
    public void test1(){
        Integer i = 100;
        Integer i2 = 100;

        Integer i3 = 300;
        Integer i4 = 300;

        System.out.println(i==i2); // 输出 true
        System.out.println(i3==i4);// 输出 false
    }

    //递归遍历
    @Test
    public void test2(){
        File file = new File("D:\\a");
        prinfFile2(file);
    }

    public void prinfFile(File file){
        System.out.println(file.getName());
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File file1 : files) {
                System.out.println("目录=========");
                prinfFile(file1);
            }
        }
    }
    public void prinfFile2(File file){

        if(file.isDirectory()){
            System.out.println("============文件夹"+file.getName());
            File[] files = file.listFiles();
            for (File file1 : files) {
                prinfFile2(file1);
            }
        }else {
            System.out.println("===========文件"+file.getName());
        }
    }


    @Test
    public void test4(){
        int a = 1;
        a = 2;
        double d =a;
        System.out.println(d);
    }

    @Test
    public void test5(){
        StringBuilder stringBuilder = new StringBuilder("abc");
        StringBuilder stringBuilder2 = stringBuilder.append("efg");
        System.out.println(stringBuilder==stringBuilder2);

    }

    @Test
    public void test6(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
    }

    @Test
    public void test7(){
        List<String> list = new ArrayList<>(3);
        for (int i=0;i<10;i++){
            list.add( "A"+i);
        }
        Iterator<String> iterator = list.iterator();
    }

    @Test
    public void test8(){
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
