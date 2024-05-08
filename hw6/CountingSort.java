package hw6;

import java.util.Arrays;

public class CountingSort {

    /**
     * Returns the largest int in int array A with O(n).
     * @param A The array to search through.
     * @return The largest int in A.
     */
    static int max(int[] A){
        int max = A[0];
        for (int i = 1; i < A.length; i++) 
            if (A[i] > max) 
                max = A[i];
        return max; 
    }

    /**
     * Sorts int array A *in place* using counting sort with O(n).
     * @param A The array to sort. Unsorted contents will be overwritten with the sorted version.
     */
    public static void sort(int[] A){
        int n = A.length;
        int k = max(A);
        int[] B = A.clone();
        int[] C = new int[k+1];

        //counting how many of each number are in A
        for (int i = 0; i < n; i++) C[A[i]]++;

        // replace each element of C with the cumulative sum of its priors
        for (int i = 1; i <= k; i++) C[i] += C[i-1];

        // traverse C backwards and place everything into B
        for (int i = n-1; i >= 0; i--){
            B[C[A[i]] -1] = A[i];
            C[A[i]]--;
        }

        // replace A with B
        for (int i = 0; i < B.length; i++) A[i] = B[i];
    }

    public static void main(String[] args) {
        int[] A = new int[]{2,5,3,0,2,3,0,3};
        int[] sorted = new int[]{0,0,2,2,3,3,3,5};
        sort(A);
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(sorted));
    }
}