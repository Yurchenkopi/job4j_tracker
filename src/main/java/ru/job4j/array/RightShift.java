package ru.job4j.array;

import java.util.Arrays;

public class RightShift {

    public static void shift(int[] nums, int count) {
        int length = nums.length;
        for (int i = 0; i < count; i++) {
            shift(nums);
        }
        System.out.println(Arrays.toString(nums));
    }

    // метод делает сдвиг с шагом 1
    private static void shift(int[] nums) {
        int buf = nums[nums.length - 1];
        for (int i = 0; i < nums.length - 1; i++) {
            nums[nums.length - 1 - i] = nums[nums.length - 2 - i];
        }
        nums[0] = buf;
    }

}
