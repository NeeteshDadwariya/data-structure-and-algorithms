

public class TernaryBitStrings {

    static int consecOneCount = 0;

    // Returns count of maximum consecutive 1's
    // in binary array arr[0..n-1]
    static int getMaxLength(int arr[], int n) {
        int result = 0;
        for (int i = 0; i < n - 2; i++)
            if ((arr[i] == 1 && arr[i + 1] == 1 && arr[i + 2] == 1))
                result++;
        return result;
    }

    // Function to print the output
    static void printTheArray(int arr[], int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        int count = getMaxLength(arr, n);
        consecOneCount += (count >= 1) ? 0 : 1;
        System.out.print("=>" + count + "\n");
    }

    // Function to generate all binary strings
    static void generateAllTernaryStrings(int n,
                                          int arr[], int i) {
        if (i == n) {
            printTheArray(arr, n);
            return;
        }

        // First assign "0" at ith position
        // and try for all other permutations
        // for remaining positions
        arr[i] = 0;
        generateAllTernaryStrings(n, arr, i + 1);

        // And then assign "1" at ith position
        // and try for all other permutations
        // for remaining positions
        arr[i] = 1;
        generateAllTernaryStrings(n, arr, i + 1);

        // And then assign "1" at ith position
        // and try for all other permutations
        // for remaining positions
        //arr[i] = 2;
        //generateAllTernaryStrings(n, arr, i + 1);
    }

    // Driver Code
    public static void main(String args[]) {
        int n = 7;

        int[] arr = new int[n];

        // Print all binary strings
        generateAllTernaryStrings(n, arr, 0);

        System.out.println(consecOneCount);
        System.out.println(foo1(n));
    }

    public static int foo(int n) {
        if (n == 0 || n == 1) return 0;
        return (int) (2 * foo(n - 1) + 2 * foo(n - 2) + Math.pow(3, n - 2));
    }

    public static int foo1(int n) {
        if (n == 0) return 1;
        if (n == 1) return 2;
        if (n == 2) return 4;
        return (int) (foo1(n-1) + foo1(n-2) + foo1(n-3));
    }
}
