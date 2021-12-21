package tree;// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import exception.UnderflowException;

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 *
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        root = insert(x, root);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x the item to remove.
     */
    public void remove(AnyType x) {
        root = remove(x, root);
    }

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public AnyType findMin() {
        if (isEmpty())
            throw new UnderflowException();
        return findMin(root).element;
    }

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item of null if empty.
     */
    public AnyType findMax() {
        if (isEmpty())
            throw new UnderflowException();
        return findMax(root).element;
    }

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
        System.out.println();
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return new BinaryNode<>(x, null, null);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return t;   // Item not found; do nothing

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) // Two children
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t != null)
            while (t.right != null)
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     *
     * @param t the node that roots the subtree.
     */
    private void printTree(BinaryNode<AnyType> t) {
        if (t != null) {
            printTree(t.left);
            System.out.print(t.element + " ");
            printTree(t.right);
        }
    }

    /**
     * Internal method to compute height of a subtree.
     *
     * @param t the node that roots the subtree.
     */
    private int height(BinaryNode<AnyType> t) {
        if (t == null)
            return -1;
        else
            return 1 + Math.max(height(t.left), height(t.right));
    }

    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType> {
        // Constructors
        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


    /**
     * The tree root.
     */
    private BinaryNode<AnyType> root;

    /**
     * Count the number of nodes in the tree
     */
    public int nodeCount() {
        return nodeCount(root);
    }

    private int nodeCount(BinaryNode<AnyType> node) {
        if (node == null) return 0;
        return 1 + nodeCount(node.left) + nodeCount(node.right);
    }

    /**
     * Test if the current tree is complete binary tree or not
     */
    public boolean isFull() {
        return isFull(root);
    }

    private boolean isFull(BinaryNode<AnyType> node) {
        if (node == null) return true;
        boolean isLeftTreePresent = node.left != null;
        boolean isRightTreePresent = node.right != null;
        boolean isNodeComplete = (isLeftTreePresent && isRightTreePresent) || (!isLeftTreePresent && !isRightTreePresent);
        return isNodeComplete && isFull(node.left) && isFull(node.right);
    }

    /**
     * Compare structures of the given tree with the input tree. If both are same in structure, return true, else false.
     * @param t2 - The tree to be compared.
     */
    public boolean compareStructure(BinarySearchTree<AnyType> t2) {
        return compareStructure(root, t2.root);
    }

    private boolean compareStructure(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 != null && node2 != null)
            return compareStructure(node1.left, node2.left)
                    && compareStructure(node1.right, node2.right);
        return false;
    }

    /**
     * Tests if the two trees are equal (by elements and by structure both)
     * @param t2 - The tree to be compared.
     */
    public boolean equals(BinarySearchTree<AnyType> t2) {
        return equals(root, t2.root);
    }

    private boolean equals(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 != null && node2 != null) {
            if (node1.element == null && node2.element == null) return true;
            return (node1.element != null && node1.element.compareTo(node2.element) == 0)
                    && equals(node1.left, node2.left)
                    && equals(node1.right, node2.right);
        }
        return false;
    }

    /**
     * Creates a deep copy of the current tree
     * @return - The copied tree.
     */
    public BinarySearchTree<AnyType> copy() {
        BinarySearchTree<AnyType> newTree = new BinarySearchTree<>();
        newTree.root = copyNode(root);
        return newTree;
    }

    private BinaryNode<AnyType> copyNode(BinaryNode<AnyType> node) {
        if (node == null) return null;
        BinaryNode<AnyType> newNode = new BinaryNode<>(node.element);
        newNode.left = copyNode(node.left);
        newNode.right = copyNode(node.right);
        return newNode;
    }

    /**
     * Creates a mirror copy of the tree.
     * @return - The mirrored tree
     */
    public BinarySearchTree<AnyType> mirror() {
        BinarySearchTree<AnyType> newTree = new BinarySearchTree<>();
        newTree.root = mirrorNode(root);
        return newTree;
    }

    private BinaryNode<AnyType> mirrorNode(BinaryNode<AnyType> node) {
        if (node == null) return null;
        BinaryNode<AnyType> newNode = new BinaryNode<>(node.element);
        newNode.right = mirrorNode(node.left);
        newNode.left = mirrorNode(node.right);
        return newNode;
    }

    /**
     * Tests if the current tree is mirror with the provided tree
     * @param t2 - The tree with which it is to be compared.
     */
    public boolean isMirror(BinarySearchTree<AnyType> t2) {
        return isMirror(root, t2.root);
    }

    private boolean isMirror(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 != null && node2 != null) {
            if (node1.element == null && node2.element == null) return true;
            return (node1.element != null && node1.element.compareTo(node2.element) == 0)
                    && isMirror(node1.left, node2.right)
                    && isMirror(node1.right, node2.left);
        }
        return false;
    }

    public BinaryNode<AnyType> find(AnyType elementToFind) {
        return find(root, elementToFind);
    }

    private BinaryNode<AnyType> find(BinaryNode<AnyType> node, AnyType elementToFind) {
        if (node == null || elementToFind == null || node.element == null) return null;
        int compareResult = node.element.compareTo(elementToFind);
        if (compareResult < 0) return find(node.right, elementToFind);
        else if (compareResult > 0) return find(node.left, elementToFind);
        return node;    // Match
    }

    /**
     * Find the parent of the given element
     * @param elementToFind
     * @return - The ParentChildPair contains both the child and the parent where the element is found.
     */
    public ParentChildPair findParent(AnyType elementToFind) {
        return findParent(root, null, elementToFind);
    }

    private ParentChildPair findParent(BinaryNode<AnyType> node, BinaryNode<AnyType> parent, AnyType elementToFind) {
        if (node == null || elementToFind == null || node.element == null) return null;
        int compareResult = node.element.compareTo(elementToFind);
        if (compareResult < 0) return findParent(node.right, node, elementToFind);
        else if (compareResult > 0) return findParent(node.left, node, elementToFind);

        return new ParentChildPair(parent, node);    // Match
    }

    /**
     * Right rotates the tree. Changes the root of the tree if the rotation expects a new root.
     * @param elementToRotate
     */
    public void rotateRight(AnyType elementToRotate) {
        if (root == null || elementToRotate == null) return;
        ParentChildPair parentChildPair = findParent(elementToRotate);

        //Element not found
        if (parentChildPair == null) return;

        BinaryNode<AnyType> parent = parentChildPair.parent;
        BinaryNode<AnyType> nodeToBeRotated = parentChildPair.child;
        BinaryNode<AnyType> leftChild = nodeToBeRotated.left;
        nodeToBeRotated.left = leftChild != null ? leftChild.right : null;
        leftChild.right = nodeToBeRotated;

        //Element to be rotated is root itself, No parent linkage change is required.
        if (parent == null) {
            //Change root of the tree if left is present.
            if (leftChild != null) root = leftChild;
            return;
        }

        //Change parent linkages
        //Element is found and parent has left linkage to it.
        if (parent.element.compareTo(nodeToBeRotated.element) > 0) {
            parent.left = leftChild;
        }
        //Element is found and parent has right linkage to it.
        else {
            parent.right = leftChild;
        }
    }

    /**
     * Left rotates the tree. Changes the root of the tree if the rotation expects a new root.
     * @param elementToRotate
     */
    public void rotateLeft(AnyType elementToRotate) {
        if (root == null || elementToRotate == null) return;
        ParentChildPair parentChildPair = findParent(elementToRotate);

        //Element not found
        if (parentChildPair == null) return;

        BinaryNode<AnyType> parent = parentChildPair.parent;
        BinaryNode<AnyType> nodeToBeRotated = parentChildPair.child;
        BinaryNode<AnyType> rightChild = nodeToBeRotated.right;
        nodeToBeRotated.right = rightChild != null ? rightChild.left : null;
        rightChild.left = nodeToBeRotated;

        //Element to be rotated is root itself, No parent linkage change is required.
        if (parent == null) {
            //Change root of the tree if right is present.
            if (rightChild != null) root = rightChild;
            return;
        }

        //Change parent linkages
        //Element is found and parent has left linkage to it.
        if (parent.element.compareTo(nodeToBeRotated.element) > 0) {
            parent.left = rightChild;
        }
        //Element is found and parent has right linkage to it.
        else {
            parent.right = rightChild;
        }
    }

    /**
     * Print the level order traversal of the tree.
     */
    public void printLevels() {
        int levels = height(root) + 1;
        for (int l = 1; l <= levels; l++)
            printLevels(root, l);
    }

    public void printLevels(BinaryNode<AnyType> node, int level) {
        if (node == null) return;
        if (level == 1)
            System.out.print(node.element + " ");
        if (level > 1) {
            printLevels(node.left, level - 1);
            printLevels(node.right, level - 1);
        }
    }

    // Test program
    public static void main(String[] args) {
        BinarySearchTree<Integer> balancedTree = new BinarySearchTree<>();
        BinarySearchTree<Integer> imbalancedTree = new BinarySearchTree<>();
        BinarySearchTree<Integer> emptyTree = new BinarySearchTree<>();
        BinarySearchTree<Integer> similarStructTree = new BinarySearchTree<>();

        int[] treeElements = new int[]{41, 20, 65, 11, 29, 25, 32, 50, 91, 72, 99};
        for (int x : treeElements)
            balancedTree.insert(x);
        balancedTree.printTree("Printing balanced tree after insertion");

        for (int x : treeElements)
            imbalancedTree.insert(x);
        imbalancedTree.remove(25);
        imbalancedTree.printTree("Printing imbalanced tree after insertion");

        for (int x : treeElements)
            similarStructTree.insert(x + 1);
        similarStructTree.printTree("Printing similarStructTree after insertion");

        testNodeCount(balancedTree, imbalancedTree, emptyTree);
        testFullTree(balancedTree, imbalancedTree, emptyTree);
        testCompareStructures(balancedTree, imbalancedTree, emptyTree, similarStructTree);
        testEqualTree(balancedTree, imbalancedTree, emptyTree, similarStructTree);
        testCopyTree(balancedTree, emptyTree);
        testMirroredTree(balancedTree, imbalancedTree, emptyTree);
        testRightRotate(41, balancedTree.copy());
        testLeftRotate(41, balancedTree.copy());
        testLevelOrderTraversal(balancedTree, imbalancedTree);
    }

    private static void testLevelOrderTraversal(BinarySearchTree<Integer> balancedTree, BinarySearchTree<Integer> imbalancedTree) {
        printMsg("\nPrint level order traversal of balancedTree.");
        balancedTree.printLevels();
        printMsg("\nPrint level order traversal of imbalancedTree.");
        imbalancedTree.printLevels();
    }

    private static void testFullTree(BinarySearchTree<Integer> balancedTree, BinarySearchTree<Integer> imbalancedTree, BinarySearchTree<Integer> emptyTree) {
        //If tree is full or not
        printMsg("Is balanced tree full: %s", balancedTree.isFull());
        printMsg("Is imbalanced tree full: %s", imbalancedTree.isFull());
        printMsg("Is empty tree full: %s", emptyTree.isFull());
    }

    private static void testNodeCount(BinarySearchTree<Integer> balancedTree, BinarySearchTree<Integer> imbalancedTree, BinarySearchTree<Integer> emptyTree) {
        //Node count of the tree
        printMsg("Node count of the balanced tree: %s", balancedTree.nodeCount());
        printMsg("Node count of the imbalanced tree: %s", imbalancedTree.nodeCount());
        printMsg("Node count of the empty tree: %s", emptyTree.nodeCount());
    }

    private static void testCompareStructures(BinarySearchTree<Integer> balancedTree, BinarySearchTree<Integer> imbalancedTree, BinarySearchTree<Integer> emptyTree, BinarySearchTree<Integer> similarStructTree) {
        printMsg("Compare structures (balanced with balanced): %s", balancedTree.compareStructure(balancedTree));
        printMsg("Compare structures (balanced with similar): %s", balancedTree.compareStructure(similarStructTree));
        printMsg("Compare structures (balanced with imbalanced): %s", balancedTree.compareStructure(imbalancedTree));
        printMsg("Compare structures (balanced with empty): %s", balancedTree.compareStructure(emptyTree));
    }

    private static void testEqualTree(BinarySearchTree<Integer> balancedTree, BinarySearchTree<Integer> imbalancedTree, BinarySearchTree<Integer> emptyTree, BinarySearchTree<Integer> similarStructTree) {
        //Equals check
        printMsg("Are tree equals (balanced with balanced): %s", balancedTree.equals(balancedTree));
        printMsg("Are tree equals (balanced with similar): %s", balancedTree.equals(similarStructTree));
        printMsg("Are tree equals (balanced with imbalanced): %s", balancedTree.equals(imbalancedTree));
        printMsg("Are tree equals (balanced with empty): %s", balancedTree.equals(emptyTree));
    }

    private static void testCopyTree(BinarySearchTree<Integer> balancedTree, BinarySearchTree<Integer> emptyTree) {
        //Copy tree
        BinarySearchTree<Integer> balancedTreeCopy = balancedTree.copy();
        balancedTreeCopy.printTree("Copied tree of balancedTree");
        emptyTree.copy().printTree("Copied tree of emptyTree");

        //Verifying that original tree is intact if copy is changed
        balancedTreeCopy.remove(32);
        balancedTreeCopy.printTree("Copied tree after removal of element");
        balancedTree.printTree("Original tree is intact");
    }

    private static void testMirroredTree(BinarySearchTree<Integer> balancedTree, BinarySearchTree<Integer> imbalancedTree, BinarySearchTree<Integer> emptyTree) {
        //Mirror tree
        BinarySearchTree<Integer> mirroredTree = balancedTree.mirror();
        mirroredTree.printTree("Mirrored tree of balancedTree");
        emptyTree.mirror().printTree("Mirrored tree of emptyTree");

        //Is mirrored tree
        printMsg("Are tree mirrored (balanced with balanced): %s", balancedTree.isMirror(balancedTree));
        printMsg("Are tree mirrored (balanced with mirrored): %s", balancedTree.isMirror(mirroredTree));
        printMsg("Are tree mirrored (balanced with imbalanced): %s", balancedTree.isMirror(imbalancedTree));
        printMsg("Are tree mirrored (balanced with empty): %s", balancedTree.isMirror(emptyTree));
    }

    private static void testRightRotate(int elementToRotate, BinarySearchTree<Integer> treeToBeRotated) {
        BinaryNode<Integer> rotationElement = treeToBeRotated.find(elementToRotate);
        treeToBeRotated.rotateRight(elementToRotate);
        BinaryNode<Integer> parentNode = treeToBeRotated.findParent(elementToRotate).parent;
        treeToBeRotated.printTree("Tree after right rotation (" + elementToRotate + ")");
        printMsg("Parent of rotation element is now: %s", nodeToString(parentNode));
        printMsg("Rotation element is now: %s", nodeToString(rotationElement));
        printMsg("Left child of rotationElement is now: %s", nodeToString(rotationElement.left));
        printMsg("Right child of rotationElement is now: %s", nodeToString(rotationElement.right));
    }

    private static void testLeftRotate(int elementToRotate, BinarySearchTree<Integer> treeToBeRotated) {
        BinaryNode<Integer> rotationElement = treeToBeRotated.find(elementToRotate);
        treeToBeRotated.rotateLeft(elementToRotate);
        BinaryNode<Integer> parentNode = treeToBeRotated.findParent(elementToRotate).parent;
        treeToBeRotated.printTree("Tree after left rotation (" + elementToRotate + ")");
        printMsg("Parent of rotation element is now: %s", nodeToString(parentNode));
        printMsg("Rotation element is now: %s", nodeToString(rotationElement));
        printMsg("Left child of rotationElement is now: %s", nodeToString(rotationElement.left));
        printMsg("Right child of rotationElement is now: %s", nodeToString(rotationElement.right));
    }

    public void printTree(String msg) {
        System.out.println(msg + ":");
        printTree();
    }

    public static <AnyType> String nodeToString(BinaryNode<AnyType> node) {
        if (node == null) return null;
        return String.format("(%s)[L=%s,R=%s]", node.element,
                (node.left != null ? node.left.element : null),
                (node.right != null ? node.right.element : null)
        );
    }

    public static void printMsg(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private class ParentChildPair {
        public BinaryNode<AnyType> parent;
        public BinaryNode<AnyType> child;

        public ParentChildPair(BinaryNode<AnyType> parent, BinaryNode<AnyType> child) {
            this.parent = parent;
            this.child = child;
        }

        @Override
        public String toString() {
            return "ParentChildPair{" +
                    "parent=" + (parent != null ? parent.element : null) +
                    ", child=" + (child != null ? child.element : null) +
                    '}';
        }
    }
}