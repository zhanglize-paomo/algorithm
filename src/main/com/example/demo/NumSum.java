package com.example.demo.algorthm;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * 寻找几个数之和从 m 个数中寻找 n 个和为 sum 的索引
 *
 * @author zhanglize
 * @create 2019/8/19
 */
public class NumSum {

    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    public static void main(String[] args) {
        //获取随机数组
        int[] nums = hashSet(buildNums());
        List<int[]> idxs = compute(nums, 2, 100);

        idxs.forEach(is -> {
            StringJoiner sj = new StringJoiner(", ", "[", "]");
            for (int i : is) {
                sj.add(i + "=" + nums[i]);
            }
            System.out.println(sj);
        });
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
     *
     *
     * @param nums  随机数组
     * @param size  数据大小
     * @param sum   求和
     * @return
     */
    private static List<int[]> compute(int[] nums, int size, int sum) {
        return compute(nums, 0, nums.length, size, sum);
    }

    /**
     * 计算选法,从 数组 nums 选择 size 个相加等于 sum 的数，start 为开始位置，stop 为结束位置
     *
     * @param start 起始点
     * @param stop  结束点
     * @param size  挑选数字的个数
     * @param sum   需要求得的和
     * @return 返回计算到的路径结果
     */
    private static List<int[]> compute(int[] nums, int start, int stop, int size, int sum) {
        List<int[]> list = emptyList();
        for (int i = start; i <= stop - size; i++) {
            list = plus(list, size == 1 ? (nums[i] == sum ? singletonList(new int[]{i}) : emptyList()) :
                    multi(Arrays.asList(new int[]{i}), compute(nums, i + 1, stop, size - 1, sum - nums[i])));

        }
        return list;
    }

    /**
     * 乘起路径
     */
    private static List<int[]> multi(List<int[]> list, List<int[]> r) {
        if(r.isEmpty() || list.isEmpty()){
            return emptyList();
        }
        List<int[]> ps = new ArrayList<>(list.size() * r.size());
        list.forEach(is1 -> r.forEach(is2 -> {
            int[] is = new int[is1.length + is2.length];
            System.arraycopy(is1, 0, is, 0, is1.length);
            System.arraycopy(is2, 0, is, is1.length, is2.length);
            ps.add(is);
        }));
        return ps;
    }

    /**
     * 收集并加起路径
     */
    private static List<int[]> plus(List<int[]> list, List<int[]> r) {
        if(r.isEmpty()){
            return list;
        }
        if(list.isEmpty()){
            return r;
        }
        if(!(list instanceof ArrayList)){
            list = new ArrayList<>(list);
        }
        list.addAll(r);
        return list;
    }


    /**
     * 随机创建一堆数字并将数组的数据进行排序
     *
     * @return
     */
    private static int[] buildNums() {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = random.nextInt(-100, 100);
        }
        Arrays.sort(nums);
        return nums;
    }

}
