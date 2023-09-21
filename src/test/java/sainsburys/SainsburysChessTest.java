package sainsburys;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class SainsburysChessTest {

    private static final String FIRST_GAME_START_POSITION = "4c";
    private static final int FIRST_GAME_ROW_MOVES = 2;
    private static final int FIRST_GAME_COLUMN_MOVES = 2;

    private static final String SECOND_GAME_START_POSITION = "8g";
    private static final int SECOND_GAME_ROW_MOVES = 4;
    private static final int SECOND_GAME_COLUMN_MOVES = 6;

    private static final String THIRD_GAME_START_POSITION = "8g";
    private static final int THIRD_GAME_ROW_MOVES = 12;
    private static final int THIRD_GAME_COLUMN_MOVES = 12;

    SainsburysChess sainsburysChess;

    @BeforeEach
    void setUp() {
        sainsburysChess = new SainsburysChess();
    }

    @Test
    @Order(1)
    @DisplayName("Test 1 -  Happy movement")
    public void firstGame() {
        final String EXPECTED = "6e";

        String endPosition = sainsburysChess.playChess(FIRST_GAME_START_POSITION, FIRST_GAME_ROW_MOVES, FIRST_GAME_COLUMN_MOVES);

        assertEquals(EXPECTED, endPosition);
    }

    @Test
    @Order(2)
    @DisplayName("Test 2 -  Boundary bounce")
    public void secondGame() {
        System.out.println("TEST TWO - testing first bounces");
        final String EXPECTED = "4c";

        String endPosition = sainsburysChess.playChess(SECOND_GAME_START_POSITION, SECOND_GAME_ROW_MOVES, SECOND_GAME_COLUMN_MOVES);

        assertEquals(EXPECTED, endPosition);
    }

    @Test
    @Order(3)
    @DisplayName("Test 2 -  Multiple boundary bounce")
    public void thirdGame() {
        System.out.println("TEST THREE - testing multiple bounces");
        final String EXPECTED = "6e";

        String endPosition = sainsburysChess.playChess(THIRD_GAME_START_POSITION, THIRD_GAME_ROW_MOVES, THIRD_GAME_COLUMN_MOVES);

        assertEquals(EXPECTED, endPosition);
    }
}
