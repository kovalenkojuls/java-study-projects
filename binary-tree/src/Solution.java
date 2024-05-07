import TreeComponent.BinaryTree;
import TreeComponent.TraversalType;

public class Solution {
    public static void main(String[] args) {
        BinaryTree binaryTree  = new BinaryTree();
        binaryTree.root = binaryTree.insertNewNode(binaryTree.root, 15);
        System.out.println("Insert node " + 15);
        binaryTree.root = binaryTree.insertNewNode(binaryTree.root, 20);
        System.out.println("Insert node " + 20);
        binaryTree.root = binaryTree.insertNewNode(binaryTree.root, 7);
        System.out.println("Insert node " + 7);
        binaryTree.root = binaryTree.insertNewNode(binaryTree.root, 3);
        System.out.println("Insert node " + 3);
        binaryTree.root = binaryTree.insertNewNode(binaryTree.root, 8);
        System.out.println("Insert node " + 8);
        binaryTree.root = binaryTree.insertNewNode(binaryTree.root, 17);
        System.out.println("Insert node " + 17);
        binaryTree.root = binaryTree.insertNewNode(binaryTree.root, 33);
        System.out.println("Insert node " + 33);
        binaryTree.root = binaryTree.insertNewNode(binaryTree.root, 19);
        System.out.println("Insert node " + 19);
        binaryTree.root = binaryTree.insertNewNode(binaryTree.root, 16);
        System.out.println("Insert node " + 16);

        binaryTree.printTree(binaryTree.root, TraversalType.LEFT_NODE_RIGHT);
        binaryTree.printTree(binaryTree.root, TraversalType.NODE_LEFT_RIGHT);
        binaryTree.printTree(binaryTree.root, TraversalType.LEFT_RIGHT_NODE);

        System.out.println();

        System.out.println("The tree contains node 20? " + (binaryTree.findNode(binaryTree.root, 20) ? "yes" : "no"));
        System.out.println("The tree contains node 11? " + (binaryTree.findNode(binaryTree.root, 11) ? "yes" : "no"));
        System.out.println("The tree contains node 19? " + (binaryTree.findNode(binaryTree.root, 19) ? "yes" : "no"));

        System.out.println();

        System.out.println("Min is node " + binaryTree.findMinNode(binaryTree.root));
        System.out.println("Max is node " + binaryTree.findMaxNode(binaryTree.root));

        System.out.println();

        System.out.println("Remove node 20");
        binaryTree.removeNode(binaryTree.root, 20);
        binaryTree.printTree(binaryTree.root, TraversalType.LEFT_NODE_RIGHT);
    }
}
