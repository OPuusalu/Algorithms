package P9;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Kordamine {
    /* This function takes last element as pivot,
   places the pivot element at its correct
   position in sorted array, and places all
   smaller (smaller than pivot) to left of
   pivot and all greater elements to right
   of pivot */
    static int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    static void sort(int arr[], int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }
    static void sortMagasin(int arr[]) {
        Deque<Integer> vasemad = new ArrayDeque<>(List.of(0));
        Deque<Integer> paremad = new ArrayDeque<>(List.of(arr.length-1));

        while (!vasemad.isEmpty()){
            int vasem = vasemad.pop();
            int parem = paremad.pop();
            int kesk = partition(arr, vasem, parem);
            if (kesk - vasem > 1){
                vasemad.push(vasem);
                paremad.push(kesk-1);
            }
            if (parem - kesk > 1){
                vasemad.push(kesk+1);
                paremad.push(parem);
            }
        }

    }

    // Driver program
    public static void main(String args[])
    {
//        int[] arr = Abi.juhumassiiv(100, 0, 9);
//        int n = arr.length;
        int[] arvud = new int[100_000];
        for (int i = 0; i < arvud.length; i++) {
            arvud[i] = i;
        }

//        System.out.println(Arrays.toString(arr));
        sortMagasin(arvud);
//        System.out.println(Arrays.toString(arr));
    }
}
