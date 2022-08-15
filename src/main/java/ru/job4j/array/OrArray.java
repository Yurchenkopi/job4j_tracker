package ru.job4j.array;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class OrArray {
    public static int[] or(int[] left, int[] right) {
        int length = left.length + right.length;
        int[] tempRsl = new int[length];
        Set<Integer> nums = new TreeSet<>();
        for (int i = 0; i < left.length; i++) {
            nums.add(left[i]);
        }
        for (int i = 0; i < right.length; i++) {
            nums.add(right[i]);
        }
        int index = 0;
        for (int i : nums) {
            tempRsl[index] = i;
            index++;
        }
        return Arrays.copyOf(tempRsl, index);
    }
}