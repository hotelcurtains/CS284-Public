package hw6;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;  

public class CountingSortTest {
    @Test
    public void test1(){
        int[] A = new int[]{2,5,3,0,2,3,0,3};
        int[] sorted = new int[]{0,0,2,2,3,3,3,5};
        CountingSort.sort(A);
        assertTrue(Arrays.equals(A,sorted));
    }
    @Test
    public void test2(){
        int[] A = new int[]{6,0,3,5};
        int[] sorted = new int[]{0,3,5,6};
        CountingSort.sort(A);
        assertTrue(Arrays.equals(A,sorted));
    }
    @Test
    public void test3(){
        int[] A = new int[]{64,25,12,22,11};
        int[] sorted = new int[]{11,12,22,25,64};
        CountingSort.sort(A);
        assertTrue(Arrays.equals(A,sorted));
    }
    @Test
    public void test4(){
        int[] A = new int[]{12,34,54,2,3};
        int[] sorted = new int[]{2,3,12,34,54};
        CountingSort.sort(A);
        assertTrue(Arrays.equals(A,sorted));
    }
}
