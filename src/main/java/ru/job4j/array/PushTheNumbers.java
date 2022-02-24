package ru.job4j.array;

import java.util.Arrays;

public class PushTheNumbers {

    public static void push(int[][] array, int row, int column) {
        int[] colArr = new int[array.length];
        int[] rowArr = new int[array[0].length];
        for (int i = 0; i < colArr.length; i++) {
            colArr[i] = array[i][column];
        }
        for (int i = 0; i < rowArr.length; i++) {
            rowArr[i] = array[row][i];
        }
        shiftAcross(colArr, row, 0);
        shiftAcross(rowArr, column, 0);
        for (int i = 0; i < colArr.length; i++) {
            array[i][column] = colArr[i];
        }
        for (int i = 0; i < rowArr.length; i++) {
            array[row][i] = rowArr[i];
        }
    }

    private static void shiftLeft(int[] nums) {
        int buf = nums[nums.length - 1];
        for (int i = 0; i < nums.length - 1; i++) {
            nums[nums.length - 1 - i] = nums[nums.length - 2 - i];
        }
        nums[0] = buf;
    }

    private static void shiftRight(int[] nums) {
        int buf = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i + 1];
        }
        nums[nums.length - 1] = buf;
    }

    private static void shiftAcross(int[] nums, int index, int fill) {
        for (int i = 0; i < index; i++) {
            nums[i] = nums[i + 1];
        }
        for (int i = 0; i < nums.length - index - 1; i++) {
            nums[nums.length - 1 - i] = nums[nums.length - 2 - i];
        }
        nums[index] = fill;
    }

    public static void main(String[] args) {
        int[][] array = {
                {62, 12, 86, 96, 2},
                {78, 98, 10, 45, 86},
                {82, 59, 47, 88, 79},
                {99, 10, 91, 73, 88},
                {32, 48, 8, 87, 55 }
        };
        push(array, 2, 2);
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
    }

}