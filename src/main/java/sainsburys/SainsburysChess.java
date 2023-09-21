package sainsburys;

import java.util.HashMap;
import java.util.Map;

public class SainsburysChess {

    private static final Map<String, Integer> columnMapping = new HashMap<>();

    private static int boundary = 8;

    private static void setUp() {
        columnMapping.put("a", 1);
        columnMapping.put("b", 2);
        columnMapping.put("c", 3);
        columnMapping.put("d", 4);
        columnMapping.put("e", 5);
        columnMapping.put("f", 6);
        columnMapping.put("g", 7);
        columnMapping.put("h", 8);
    }

    public static String playChess(String startPosition, int rowMoves, int columnMoves) {
        // startPostion example: 4c
        // columnMoves example: 8
        // rowMoves example: 4
        setUp();

        int rowPosition = findTargetPosition(startPosition, rowMoves, Boolean.TRUE);
        int columnPosition = findTargetPosition(startPosition, columnMoves, Boolean.FALSE);

        System.out.println("Final row position: " + rowPosition);
        System.out.println("Final column position: " + columnPosition);

        String endPosition = buildEndPosition(rowPosition, columnPosition);
        System.out.println("Returning endPosition: " + endPosition);
        return endPosition;
    }

    private static int findTargetPosition(String startPosition, int moves, boolean movingRow) {
        System.out.println("");
        System.out.println("=== Calculating ===");
        System.out.println("Startposition: '" + startPosition + "' moves: '" + moves + "' movingRow: '" + movingRow + "'");
        int originalPosition;
        int currentPosition;
        boolean normalDirection = true;

        if (movingRow == true) {
            originalPosition = Integer.parseInt(startPosition.substring(0, 1));
            System.out.println("Mode: Row");
        } else {
            originalPosition = columnMapping.get(startPosition.substring(1, 2));
            System.out.println("Mode: Column");
        }
        currentPosition = originalPosition;
        System.out.println("Original position: " + currentPosition);

        while (true) {
            System.out.println("Current position: " + currentPosition);
            if (moves == 0) {
                break;
            }
            if (normalDirection) {
                if (currentPosition == boundary) {
                    normalDirection = false; // FIXES FIRST BOUNCE
                    currentPosition--;
                } else {
                    currentPosition++;
                }
            } else {
                if (currentPosition == 1) {
                    normalDirection = true;
                    currentPosition++;
                } else {
                    currentPosition--;
                }
            }
            moves--;
        }
        System.out.println("=== Finished Calculating ===");
        System.out.println("");
        return currentPosition;
    }

    private static String buildEndPosition(int row, int column) {

        return "" + row + columnMapping.entrySet().stream().filter(entry -> entry.getValue() == column).map(entry -> entry.getKey()).findFirst().get();
    }

}
