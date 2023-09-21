package sainsburys;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SainsburysChess {

    private static final Logger LOG = LoggerFactory.getLogger(SainsburysChess.class);

    private static final Map<String, Integer> columnMappingKeyString = new HashMap<>();
    private static Map<Integer, String> columnMappingKeyInteger = new HashMap<>();

    private static int boundary = 8;

    private static void setUp() {
        columnMappingKeyString.put("a", 1);
        columnMappingKeyString.put("b", 2);
        columnMappingKeyString.put("c", 3);
        columnMappingKeyString.put("d", 4);
        columnMappingKeyString.put("e", 5);
        columnMappingKeyString.put("f", 6);
        columnMappingKeyString.put("g", 7);
        columnMappingKeyString.put("h", 8);

        columnMappingKeyInteger = columnMappingKeyString.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    public static String playChess(String startPosition, int rowMoves, int columnMoves) {
        // startPostion example: 4c
        // columnMoves example: 8
        // rowMoves example: 4
        setUp();

        int rowPosition = findTargetPosition(startPosition, rowMoves, true);
        int columnPosition = findTargetPosition(startPosition, columnMoves, false);
        LOG.info("Endposition - Row: {}", rowPosition);
        LOG.info("Endposition - Column: {}", rowPosition);

        String endPosition = buildEndPosition(rowPosition, columnPosition);
        LOG.info("Endposition - Combined: {}", endPosition);
        return endPosition;
    }

    private static int findTargetPosition(String startPosition, int moves, boolean movingRow) {
        LOG.info("Start Position: {} Total Moves: {}", startPosition, moves);
        int originalPosition;
        int currentPosition;
        boolean normalDirection = true;

        if (movingRow == true) {
            originalPosition = Integer.parseInt(String.valueOf(startPosition.charAt(0)));
            LOG.info("Mode: Row");
        } else {
            originalPosition = columnMappingKeyString.get(String.valueOf(startPosition.charAt(1)));
            LOG.info("Mode: Column");
        }

        currentPosition = originalPosition;

        while (true) {
            LOG.info("Current Position: {}", currentPosition);
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
        return currentPosition;
    }

    private static String buildEndPosition(int row, int column) {

        return "" + row + columnMappingKeyInteger.get(column);
    }

}
