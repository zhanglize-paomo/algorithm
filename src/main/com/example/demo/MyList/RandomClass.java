package com.example.demo.MyList;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhanglize
 * @create 2019/9/13
 */
public class RandomClass {

    public static void main(String[] args) {
//        randoms();
//        System.out.println(price(5));
//        System.out.println(regex());
        System.out.println("24214215215:"+ num("423424235235235"));
    }

    private static int num(String string) {
        List<String> list = numCount(string);
        if(list.size() == 1){
            return Integer.parseInt(list.get(0));
        }else{
            list = numCounts(list);
            String strings = "";
            for(String str : list){
                strings += str;
            }
            if(1 != strings.length()){
                num(strings);
            }
        }
        return Integer.parseInt(list.get(0));
    }

    private static List<String> numCounts(List<String> list) {
        int a = 0;
        //将集合中的数组相加
        for(String strings : list){
            a += Integer.parseInt(strings);
        }
        List<String> stringList = new ArrayList<>();
        for(int i = 0 ; i< String.valueOf(a).trim().length() ; i++){
            stringList.add(String.valueOf(a).substring(i,i+1));
        }
        return stringList;
    }

    /**
     * 将一组数字的各个位数相加和为一位数的时候将其值输出出来
     *
     * @param string
     */
    private static List<String> numCount(String string) {
        String str = string.trim();
        List<String> list = new ArrayList<>();
        for(int i = 0 ; i< str.length() ; i++){
            list.add(str.substring(i,i+1));
        }
        int a = 0;
        //将集合中的数组相加
        for(String strings : list){
           a += Integer.parseInt(strings);
        }
        List<String> stringList = new ArrayList<>();
        for(int i = 0 ; i< String.valueOf(a).trim().length() ; i++){
            stringList.add(String.valueOf(a).substring(i,i+1));
        }
        return stringList;
    }

    /**
     * 使用正则表达式进行字符串的截取
     *
     */
    private static String regex() {
        String str = "2324dsfwef423423sfsfsfSADSAFFA";
//        String regex = "[^0-9]";
        String regex = "[^A-Z]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 北京地铁的交通规则
     * 超过100元打八折,超过150元打5折
     *
     * @param j
     * @return
     */
    private static int price(int j) {
        int sum = 0;
        int num = 0;
        for(int i = 0 ;i < 22 ; i++){
            num += 2 * j;
            if(num < 100){
                sum = num;
            }else if(num > 100 && num < 150){
                sum = (int) (100 + (num - 100) * 0.8);
            }else{
                //金额大于150
                sum = (int) (100 + 40 + (num - 150) * 0.5);
            }
        }
        return sum;
    }

    /**
     * 获取随机的数据并将数据进行排序
     * 从大到小直接使用  Collections.sort(List<T> list);
     * 自定义的数据排序  Collections.sort(List<T> list,Comparator<? super T> c);
     *
     */
    private static void randoms() {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for(int i = 0 ;i < 10 ; i++){
            //从0-100的随机数中选取随机的10个数字
            list.add(random.nextInt(100));
        }
        //获取数组中的最大的元素
        System.out.println(Collections.max(list));
        //获取数组中最小的元素
        System.out.println(Collections.min(list));
        //自定义的将集合中的数据进行排序
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(list);
    }

}
