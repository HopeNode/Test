package com.hlet.practice;

public class TestLeco {
    //双指针解决
    public static int removeDuplicates(int[] A) {
        //边界条件判断
        if (A == null || A.length == 0)
            return 0;
        int left = 0;
        for (int right = 1; right < A.length; right++) {
            System.out.println(A[left] + "====" + A[right]);
            if (A[left] != A[right])
                A[++left] = A[right];
        }
        //如果左指针和右指针指向的值一样，说明有重复的，
        //这个时候，左指针不动，右指针继续往右移。如果他俩
        //指向的值不一样就把右指针指向的值往前挪

        return ++left;
    }


    /**
     * 给定一个数组需要 将数组内所有元素向右移动K个值
     *
     * @param source
     * @param k
     * @return [1, 2, 3, 4, 5, 6]    k= 2   ===>  [5,6,1,2,3,4]
     */
    public static int[] rotateArray(int[] source, int k) {
        int length = source.length;

        int[] result = new int[length];
        for (int i = 1; i <= k; i++) {
            result[i - 1] = source[length - i];
        }
        for (int i = 0; i < length - k; i++) {
            result[i + k] = source[i];
        }

        return result;
    }

    public static void main(String[] args) {

        //3,3,2,1,1,0,0   11223
        int[] A = {1, 2, 3, 4, 5, 6,7};
        int[] ints = rotateArray(A, 3);

       for(int i = 0;i<ints.length;i++){
           System.out.println(ints[i]);
       }
    }
}
