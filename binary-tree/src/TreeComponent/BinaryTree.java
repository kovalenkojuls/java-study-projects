package TreeComponent;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTree {
    public Node root;

    public Node insertNewNode(Node root, int nodeValue) {
        if (root == null) {
            root = new Node(nodeValue);
            return root;
        }

        if (nodeValue < root.nodeValue) {
            root.leftChild = insertNewNode(root.leftChild, nodeValue);
        } else {
            root.rightChild = insertNewNode(root.rightChild, nodeValue);
        }

        return root;
    }

    public boolean findNode(Node root, int value){
        if(root == null) return false;
        if(root.nodeValue == value) return true;
        if(value < root.nodeValue){
            return findNode(root.leftChild, value);
        } else {
            return findNode(root.rightChild, value);
        }
    }

    public Integer findMinNode(Node root) {
        if (root == null) return null;
        if (root.leftChild == null) return root.nodeValue;
        return findMinNode(root.leftChild);
    }

    public Integer findMaxNode(Node root){
        if (root == null) return null;
        if (root.rightChild == null) return root.nodeValue;
        return findMaxNode(root.rightChild);
    }

    public void printTreeNodeLeftRight(Node root) {
        System.out.println("print tree NODE -> LEFT CHILD -> RIGHT CHILD:");
        if (root == null) {
            System.out.println("[]");
            return;
        }

        ArrayList<Integer> resultList = new ArrayList<Integer>();
        Stack<Node> stack = new Stack<Node>();

        Node currentNode = root;
        while (true) {
            if (currentNode == null) {
                if (stack.empty()) break;

                currentNode = stack.pop();
                currentNode = currentNode.rightChild;
            } else {
                resultList.add(currentNode.nodeValue);
                stack.push(currentNode);
                currentNode = currentNode.leftChild;
            }
            //Debug.printTreeByStep(resultList, stack, null);
        }

        System.out.println(resultList);
        System.out.println();
    }

    public void printTreeLeftNodeRight(Node root) {
        System.out.println("print tree LEFT CHILD -> NODE -> RIGHT CHILD:");
        if (root == null) {
            System.out.println();
            return;
        };

        ArrayList<Integer> resultList = new ArrayList<Integer>();
        Stack<Node> stack = new Stack<Node>();

        Node currentNode = root;
        while (true) {
            if (currentNode == null) {
                if (stack.empty()) break;

                currentNode = stack.pop();
                resultList.add(currentNode.nodeValue);
                currentNode = currentNode.rightChild;
            } else {
                stack.push(currentNode);
                currentNode = currentNode.leftChild;
            }
            //Debug.printTreeByStep(resultList, stack, null);
        }

        System.out.println(resultList);
        System.out.println();
    }

    public void printTreeLeftRightNode(Node root) {
        System.out.println("print tree LEFT CHILD -> RIGHT CHILD -> NODE:");
        if (root == null) {
            System.out.println();
            return;
        }

        ArrayList<Integer> resultList = new ArrayList<Integer>();
        Stack<Integer> stackVisit = new Stack<Integer>();
        Stack<Node> stack = new Stack<Node>();

        stack.push(root);
        stackVisit.push(0);

        while (!stack.empty()) {
            int visit = stackVisit.pop();
            Node node = stack.peek();

            if (visit == 0) {
                stackVisit.push(1);
                if (node.leftChild != null) {
                    stack.push(node.leftChild);
                    stackVisit.push(0);
                }
            } else if (visit == 1) {
                stackVisit.push(2);
                if (node.rightChild != null) {
                    stack.push(node.rightChild);
                    stackVisit.push(0);
                }
            } else if (visit == 2){
                stack.pop();
                resultList.add(node.nodeValue);
            }
            //Debug.printTreeByStep(resultList, stack, stackVisit);
        }

        System.out.println(resultList);
    }

    public void printTree(Node root, TraversalType traversalType) {
        switch (traversalType) {
            case LEFT_NODE_RIGHT -> printTreeLeftNodeRight(root);
            case NODE_LEFT_RIGHT -> printTreeNodeLeftRight(root);
            case LEFT_RIGHT_NODE -> printTreeLeftRightNode(root);
        }
    }

    public Node removeNode(Node root, int value){
        if(root == null)  return root;

        if (value < root.nodeValue) {
            root.leftChild = removeNode(root.leftChild, value);
        } else if(value > root.nodeValue) {
            root.rightChild = removeNode(root.rightChild, value);
        } else {
            if(root.leftChild == null && root.rightChild == null) {
                root = null;
            } else if (root.rightChild == null) {
                root = root.leftChild;
            } else if (root.leftChild == null) {
                root = root.rightChild;
            } else {
                root.nodeValue = findMaxNode(root.leftChild);
                root.leftChild = removeNode(root.leftChild, root.nodeValue);
            }
        }
        return root;
    }
}
