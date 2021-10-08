package linkedlists;

public class Test {

    public static <AnyType extends Comparable<? super AnyType>> AnyType findMax(AnyType[] arr) {
        int maxIndex = 0;

        for (int i = 1; i < arr.length; i++)
            if (arr[i].compareTo(arr[maxIndex]) > 0)
                maxIndex = i;

        return arr[maxIndex];
    }

    public static void main(String[] args) {
        Shape arr[] = new Shape[1];
        arr[0] = new Shape();
        System.out.println(findMax(arr));
    }
}

class Shape implements Comparable<Shape> {
    @Override
    public int compareTo(Shape o) {
        return 0;
    }
}

class Square extends Shape {
    @Override
    public int compareTo(Shape o) {
        return 0;
    }
}