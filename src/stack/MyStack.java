package stack;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.stream.Collectors;

/**
 * A MyStack class that uses an ArrayList to make a stack.
 */
public class MyStack<T> {

    private ArrayList<T> stackArr;

    public MyStack() {
        stackArr = new ArrayList<>();
    }

    /**
     * Pushes the item onto the top of the stack.
     *
     * @param element - The element to be inserted in the stack.
     */
    public void push(T element) {
        stackArr.add(element);
    }

    /**
     * Pops out the last element in the stack.
     *
     * @return - The popped element
     * @throws EmptyStackException if this stack is empty.
     */
    public T pop() {
        if (stackArr.isEmpty())
            throw new EmptyStackException();
        return stackArr.remove(stackArr.size() - 1);
    }

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return the object at the top of this stack.
     * @throws EmptyStackException if this stack is empty.
     */
    public T peek() {
        if (stackArr.isEmpty())
            throw new EmptyStackException();
        return stackArr.get(stackArr.size() - 1);
    }

    /**
     * Tests if this stack is empty.
     * @return true if the stack is empty or else false
     */
    public boolean empty() {
        return stackArr.size() == 0;
    }

    @Override
    public String toString() {
        return new StringBuilder("[ ")
                .append(stackArr.stream().map((e) -> e + " ").collect(Collectors.joining()))
                .append("]")
                .toString();
    }

    /**
     * Checks the balance of the given parentheses expression.
     * @param parenthesesSeq - The input to be tested
     * @return - True if the parentheses are matched, else throw exception.
     */
    public static boolean isBalancedParentheses(String parenthesesSeq) {

        //If the original string is null/empty, it is assumed to be a balanced one.
        if (parenthesesSeq == null || parenthesesSeq.isEmpty())
            return true;

        //Create stack of character
        MyStack<Character> parenthesesStack = new MyStack<>();
        String openingParentheses = "[{(";
        String closingParentheses = "]})";

        for (char ch : parenthesesSeq.toCharArray()) {
            int openingParenthesesIndex = openingParentheses.indexOf(ch);
            int closingParenthesesIndex = closingParentheses.indexOf(ch);

            //If char is opening parentheses
            if (openingParenthesesIndex >= 0)
                parenthesesStack.push(ch);

                //Else If char is opening parentheses
            else if (closingParenthesesIndex >= 0) {
                //If the stack is already empty, or the popped parentheses is not matching.
                if (parenthesesStack.empty() ||
                        (closingParenthesesIndex != openingParentheses.indexOf(parenthesesStack.pop()))) {
                    //If unbalanced is needed, then false can be returned from here.
                    throw new RuntimeException("The given expression is not balanced.");
                }
            }

            //If parentheses are not present in string.
            else
                throw new IllegalArgumentException("The provided string contains other than opening and closing brackets.");

        }

        //At the end of the algorithm, if the stack is empty, then the parentheses are balanced.
        return parenthesesStack.empty();
    }

    public static void main(String[] args) {
        //Testing MyStack
        MyStack<Integer> stack = new MyStack<>();

        //Pushing into stack
        for (int i = 10; i <= 20; i++)
            stack.push(i);

        System.out.println(String.format("After adding stack elements:\n%s", stack));

        //Popping from stack
        stack.pop();
        stack.pop();
        System.out.println(String.format("After popping elements:\n%s", stack));

        //Test balance parentheses problem
        testBalanceParentheses("[({}{})]");
        testBalanceParentheses("[({})]");
        //testBalanceParentheses("[({}{})]]");          //Throws exception
        //testBalanceParentheses("[({)}]");             //Throws exception
    }

    private static void testBalanceParentheses(String expression) {
        System.out.println(String.format("Given expression=%s is %s balanced.", expression,
                isBalancedParentheses(expression) ? "" : "not"));
    }
}
