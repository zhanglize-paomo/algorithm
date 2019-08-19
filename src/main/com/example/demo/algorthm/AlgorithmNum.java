package com.example.demo.algorthm;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 实现--从长度为n的数组(元素互不相同)中任意选择m个数的所有组合
 * 1.手动输入数组的长度
 * 2.手动输入组合的个数
 * 3.随机数生成一个数组
 * 4.在构建数组时要对数组中的元素进行判断,保证新生成的数组与之前的数不同
 *
 * @author zhanglize
 * @create 2019/8/19
 */
public class AlgorithmNum {

    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    public static void main(String[] args) {
        //获取随机的数组信息
        int[] num = hashSet(buildNums());
        int count = solution(num, num.length - 1, 3, 10);
        System.out.println(count);
    }

    private static int[] hashSet(int[] ints) {
        // 去重
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < ints.length; i++){
            hashSet.add(ints[i]);
        }
        // 利用TreeSet排序
        Set<Integer> set = new TreeSet(hashSet);
        Integer[] integers = set.toArray(new Integer[]{});
        // 我们排序、去重后的结果数组
        int[] result = new int[integers.length];
        for (int i = 0; i < integers.length; i++){
            result[i] = integers[i].intValue();
        }
        return result;
    }

    /**
     * arr数组中，前end个数，取k个，和为sum的组合种类（取法）
     *
     * @param arr
     * @param end
     * @param k
     * @param sum
     * @return
     */
    static int solution(int[] arr, int end, int k, int sum) {
        if (end + 1 < k || end < 0 || k < 0) {
            return 0;
        }
        if (sum == 0 && k == 0) {
            return 1;
        }
        if (k == 0 && sum != 0) {
            return 0;
        }
        int[][] result = new int[2][end + 1];
        result[0][k - 1] = 0;
        result[1][k - 1] = init(arr, end, k, sum);
        //状态递推公式
        for (int i = k; i <= end; i++) {
            //result[0][i] 表示第i个数arr[i]不取时，有多少种组合
            result[0][i] = result[0][i - 1] + result[1][i - 1];
            //result[1][i]  表示第i个数取时，有多少中组合
            result[1][i] =  solution(arr, i - 1, k - 1, sum - arr[i]);
        }
        return result[0][end] + result[1][end];
    }

    /**
     * 初值初始化计算
     *
     * @param arr
     * @param end
     * @param k
     * @param sum
     * @return
     */
    static int init(int[] arr, int end, int k, int sum) {
        if (sum == 0 && k == 0) {
            return 1;
        }
        if (end < 0 || k < 1 || end + 1 < k) {
            return 0;
        }
        int count = 0;
        //获取数组的大小
        int[] num = new int[arr.length];
        for (int i = 0; i < k; i++) {
            count += arr[i];
            //往数组中添加数据
            num[i] = arr[i];
        }
        List<int[]> list = new ArrayList<>();
        if (count == sum) {
            list.add(num);
            return 1;
        }
        return 0;
    }

    /**
     * 随机创建一堆数字
     */
    private static int[] buildNums() {
        int[] nums = new int[10];
        for (int i = 0; i < 10; i++) {
            nums[i] = random.nextInt(0, 10);
        }
        Arrays.sort(nums);
        return nums;
    }


}
