package component;

import java.util.Scanner;

public class ConsoleHelper {
    private static Scanner scanner = new Scanner(System.in);
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        return scanner.nextLine();
    }

    public static int readInt() {
        return scanner.nextInt();
    }
}
