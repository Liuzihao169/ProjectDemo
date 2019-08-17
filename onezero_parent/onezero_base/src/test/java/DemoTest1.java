import org.junit.Test;

import java.util.*;

/**
 * @author hao
 * @create 2019-07-16 ${TIM}
 */
public class DemoTest1 {
    @Test
    public void test(){
        System.out.println(2^3);
        char[] chars = "asdc".toCharArray();
        System.out.println(Arrays.toString(chars));
        List<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        StringBuilder stringBuilder = new StringBuilder(1);
        stringBuilder.append(1);
        stringBuilder.append(123);
        System.out.println(stringBuilder.toString());


    }

    @Test
    public void test2(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        Collections.reverse(list);
        System.out.println(list);
        char [] i = new char[] {'1','2'};
        char[] chars = (123 + "").toCharArray();
        char[] chars1 = "1234".toCharArray();
        for (int j = 0; j < chars1.length; j++) {
            System.out.println(chars1[chars1.length-1-j]);
        }
        int  fa = 0;
        System.out.println();
        System.out.println(Integer.MAX_VALUE+1);
    }

    @Test
    public void testa2(){
        for (int i = 0; i < 10; ++i) {
            System.out.println(i);
        }
    }
    @Test
    public void test4(){
    ListNode listNode1 = new ListNode(9);

        ListNode listNode11 = new ListNode(1);
        ListNode listNode22 = new ListNode(9);
        ListNode listNode33 = new ListNode(9);
        ListNode listNode44 = new ListNode(9);
        ListNode listNode55 = new ListNode(9);
        ListNode listNode66 = new ListNode(9);
        ListNode listNode77 = new ListNode(9);
        ListNode listNode88 = new ListNode(9);
        ListNode listNode99 = new ListNode(9);

        listNode11.next=listNode22;
        listNode22.next = listNode33;
        listNode33.next = listNode44;
        listNode44.next = listNode55;
        listNode55.next = listNode66;
        listNode66.next = listNode77;
        listNode77.next = listNode88;
        listNode88.next = listNode99;
        long l = listNodeTo(listNode11);
        System.out.println(l);
     ListNode listNode = toNodeList(listNodeTo(listNode1) + listNodeTo(listNode11));
       System.out.println(listNode);

    }


    public ListNode toNodeList(long sum){
        char[] chars = (sum+"").toCharArray();
        ListNode node = new ListNode(Integer.parseInt(chars[chars.length-1]+""));
        ListNode last = node;
        for(int i = 1;i<chars.length;i++){
            ListNode temp = new ListNode(Integer.parseInt(chars[chars.length-1-i]+""));
            last.next = temp;
            last = temp;
        }
        return node;
    }


     long listNodeTo(ListNode l){


        List<Integer>list = new ArrayList<Integer>();
        while(l!=null){
            list.add(l.val);
            l = l.next;
        }
        Collections.reverse(list);
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i<list.size();i++){
            builder.append(list.get(i));
        }
        return Long.parseLong(builder.toString());
    }





}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x;
    }


}