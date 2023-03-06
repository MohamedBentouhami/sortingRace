package model;

import java.util.Random;

public class ArrayGenerator {

    /**
     * Simple constructor of the ArrayGenerator class
     */
    public ArrayGenerator() {

    }

    /**
     * generate an array with random values
     *
     * @param size the given size of array
     * @return array with random values
     */
    public int[] randomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(8045 - 122 + 1) + 769;

        }
        return array;
    }


}
