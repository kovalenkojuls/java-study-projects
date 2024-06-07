
public class MatrixTraversingSpiral {
    private static final int size = 4;
    private static int value = 1;
    private static final int[][] matrix = new int[size][size];
    public static void main(String[] args) {
        int lineTop = 0, lineBottom = size - 1, columnRight = size - 1,
                columnLeft = 0, a = 0, b = size - 1;

        do {
            leftToRight(lineTop++, a, b);
            topToBottom(columnRight--, a + 1, b--);
            rightToLeft(lineBottom--, b, a++);
            bottomToTop(columnLeft++, b, a);
        } while (a != size/2 + 1);

        printMatrix();
    }

    private static void bottomToTop(int column, int start, int end) {
        for (int i = start; i >= end ; i--) {
            matrix[i][column] = value++;
        }
    }

    private static void rightToLeft(int line, int start, int end) {
        for (int i = start; i >= end ; i--) {
            matrix[line][i] = value++;
        }
    }

    private static void leftToRight(int line, int start, int end) {
        for (int i = start; i <= end; i++) {
            matrix[line][i] = value++;
        }
    }

    private static void topToBottom(int column, int start, int end) {
        for (int i = start; i <= end ; i++) {
            matrix[i][column] = value++;
        }
    }

    private static void printMatrix() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print((matrix[i][j] < 10 ? "0" : "") + matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
