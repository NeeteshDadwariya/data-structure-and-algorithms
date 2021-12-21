package sorting;

public class QuickSort {

    private static final int CUTOFF = 3;

    private static <AnyType extends Comparable<? super AnyType>> void swapReferences(AnyType a[], int idx1, int idx2) {
        AnyType temp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = temp;
    }

    private static <AnyType extends Comparable<? super AnyType>>
    AnyType median3(AnyType[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0)
            swapReferences(a, left, center);
        if (a[right].compareTo(a[left]) < 0)
            swapReferences(a, left, right);
        if (a[right].compareTo(a[center]) < 0)
            swapReferences(a, center, right);
        print("sorting median", a, left, right);
        // Place pivot at position right - 1
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    private static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType a[], int left, int right) {
        print("start quicksort", a, left, right);
        if (left + CUTOFF <= right) {
            AnyType pivot = median3(a, left, right);
            print("after median3", a, left, right);

            // Begin partitioning
            int i = left, j = right - 1;
            for (; ; ) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                if (i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }

            print("after partitioning", a, left, right);
            swapReferences(a, i, right - 1);   // Restore pivot
            print("restore pivot", a, left, right);
            quicksort(a, left, i - 1);    // Sort small elements
            quicksort(a, i + 1, right);   // Sort large elements
        } else  // Do an insertion sort on the subarray
            insertionSort(a, left, right);
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void insertionSort(AnyType[] a, int left, int right) {
        for (int p = left + 1; p <= right; p++) {
            AnyType tmp = a[p];
            int j;

            for (j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j--)
                a[j] = a[j - 1];
            a[j] = tmp;
        }
        print("insertion sort", a, left, right);
    }

    private static <AnyType extends Comparable<? super AnyType>> void print(String msg, AnyType a[], int left, int right) {
        if (a != null) {
            System.out.println(msg + ": (" + left + "," + right + ")");
            for (int i = left; i <= right; i++)
                System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void insertionSort(AnyType[] a) {
        int j;

        for (int p = 1; p < a.length; p++) {
            AnyType tmp = a[p];
            for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--)
                a[j] = a[j - 1];
            a[j] = tmp;
        }
    }

    public static void main(String[] args) {
        Integer arr[] = new Integer[]{64, 12, 68, 23, 97, 38, 81, 76, 55, 32, 48, 29, 46};
        quicksort(arr, 0, arr.length - 1);
        print("final sorted array", arr, 0, arr.length - 1);
    }
}
