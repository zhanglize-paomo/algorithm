package com.example.demo.algorthm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 二分算法
 *
 * @author zhanglize
 * @create 2019/8/19
 */
public class AlgorithmNum {

    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    public static void main(String[] args) {
        //获取随机的数组信息
        int[] num = buildNums();
        //快速找到一个数组中的俩个数字,让这俩个数字之和等于一个给定的值
        System.out.println(array(num,100,2));
    }

    /**
     * 随机创建一堆数字
     */
    private static int[] buildNums() {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = random.nextInt(0, 100);
        }
        Arrays.sort(nums);
        return nums;
    }

    /**
     * 本方法适用于俩个数字的时候
     * 快速找到一个数组中的俩个数字,让这俩个数字之和等于一个给定的值
     *
     * 1.判断数组的数量是否大于1,如果大于1，判断数组中的数字是否有大于和的数字,并将该数组进行排序
     * 2.采用二分计算法,将数组中的数据划分为俩个不同的数组信息
     * 3.将其中的某个数组与原数组进行循环遍历获得俩个数据的和是否与和相等,如果相等将该组合方式输出出来
     *
     * @param arr  数组大小
     * @param sum  和
     * @param num  数字的个数
     * @return
     */
    private static int array(int[] arr, int sum, int num) {
        int count = 0;
        List<Integer> list = new ArrayList<>(arr.length);
        //查找区间的左端点
        int L = 0;
        //查找区间的右端点
        int R = arr.length - 1;
        //如果区间不为空继续查找
        if (L <= R) {
            int mid = L + (R - L) / 2;
            for (int i = 0; i < mid; i++) {
                list.add(arr[i]);
            }
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < arr.length; j++) {
                    if (list.get(i) != arr[j]) {
                        if (list.get(i) + arr[j] == sum) {
                            count += 1;
                            System.out.println(list.get(i) + ":" + arr[j]);
                        }
                    }
                }
            }
        }
        return count;
    }

}
