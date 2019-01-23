/**
 * 5 common sort algorithms: Insertion, Selection, Merge, Quick, Rainbow 
 *  
 * @author Sulong
 *
 */

public class Sort {
    //Insertion sort => card game => insert right number into its position
    public static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j-1]) {
                    swap(array, j, j-1);
                }
            }
        }
        return array;
    }
    
    // Selection sort => find the global minimum and swap 
    public static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = array[i];
            for (int j = i; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    swap(array, i, j);
                }
            }
        }
        return array;
    }
    
    // Merge Sort => divide into individual and merge according to ascending order 
    public static int[] mergeSort(int[] array) {
        int[] helper = new int[array.length];
        mergeSort(array, helper, 0, array.length-1);
        return array;
    }
    
    private static void mergeSort(int[] array, int[] helper, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(array, helper, left, mid);
        mergeSort(array, helper, mid + 1, right);
        for (int i = 0; i < array.length; i++) {
            helper[i] = array[i];
        }
        merge(array, helper, left, mid, right);
    }
    
    private static void merge(int[] array, int[] helper, int left, int mid, int right) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        while (leftIndex <= mid && rightIndex <= right) {
            if (helper[leftIndex] <= helper[rightIndex]) {
                array[left++] = helper[leftIndex++];
            } else {
                array[left++] = helper[rightIndex++];
            }
        }
        while (leftIndex <= mid) {
            array[left++] = helper[leftIndex++];
        }
    }
    
    public static int[] quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }
    
    // Quick Sort => partition to select a pivot index and then sort
    private static void quickSort(int[] array, int left, int right) {
        if (left >= right) return;
        int pivot = partition(array, left, right);
        quickSort(array, left, pivot - 1);
        quickSort(array, pivot + 1, right);
    }
    
    private static int partition(int[] array, int left, int right) {
        int pivot = right;
        right--;
        while (left <= right) {
            if (array[left] <= array[pivot]) {
                left++;
            } else if (array[right] >= array[pivot]) {
                right--;
            } else {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        swap(array, left, pivot);
        return left;
    }
    
    // Rainbow Sort => [0, i) NEG  [i, j) ZERO [j, k] ? (k, end] POS
    public static int[] rainbowSort(int[] array) {
        int i = 0, j = 0;
        int k = array.length - 1;
        while (j<=k) {
           if (array[j] < 0) {
               swap(array, i, j);
               i++;
               j++;
           } else if (array[j] == 0) {
               j++;
           } else {
               swap(array, j, k);
               k--;
           }
        }
        
        return array;
    }
    
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    private static void print(int[] array, String name) {
        System.out.print(name + ": ");
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    public static int[] createArray() {
      int[] array = new int[] {2, -3, 8, -9, -7, 6, -5, 4, -1, 0};
      return array;
    }
    
    
    public static void main(String[] args) {
        print(insertionSort(createArray()), "Insertion");
        print(selectionSort(createArray()), "Selection");
        print(mergeSort(createArray()), "MergeSort");
        print(quickSort(createArray()), "QuickSort");
        print(rainbowSort(createArray()), "RainbowSort");
        
    }
        
}
