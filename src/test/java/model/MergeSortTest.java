/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mohamed
 */
public class MergeSortTest {
    

    /**
     * Test of sortArray method, of class MergeSort.
     */
    @Test
    public void testSortArray() {
        int[] arr = {105,1,32};
        Sort instance = new MergeSort();
        instance.sortArray(arr);
        int[] result = {1,32,105};
        assertArrayEquals(arr,result);
    }
    /**
     * Test of sortArray method, of class MergeSort.
     */
    @Test
    public void testSortArray2() {
        int[] arr = {105,1,32,92,45,65,1,850,7,0,9};
        Sort instance = new MergeSort();
        instance.sortArray(arr);
        int[] result = {0,1,1,7,9,32,45,65,92,105,850};
        assertArrayEquals(arr,result);
    }
    /**
     * Test of sortArray method, of class MergeSort.
     */
    @Test
    public void testSortArray3() {

        int[] arr = {105,1,32};
        Sort instance = new MergeSort();
        instance.sortArray(arr);
        int[] result = {1,32,105};
        assertArrayEquals(arr,result);
    }



    
}
