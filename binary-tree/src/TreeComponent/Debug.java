package TreeComponent;

import java.util.ArrayList;
import java.util.Stack;

public class Debug {
    public static void printTreeByStep(ArrayList<Integer> resultList, Stack<Node> stack, Stack<Integer> stackVisit) {
        System.out.print("Stack: [");
        stack.forEach(node -> System.out.print(node.nodeValue + " "));
        System.out.print("]\n");

        if (stackVisit != null) {
            System.out.print("stackVisit: [");
            stackVisit.forEach(node -> System.out.print(node + " "));
            System.out.print("]\n");
        }

        System.out.println("ResultList: " + resultList);
        System.out.println();
    }
}
