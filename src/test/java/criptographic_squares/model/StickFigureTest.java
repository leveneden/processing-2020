package criptographic_squares.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CASOS DE PRUEBA
 */
class StickFigureTest {

    /*
    NAMING CONVENTION:
    methodName_StateUnderTest_ExpectedBehavior
     */

    private StickFigure square;

    private final int INNER_SQUARE_SIZE = 10;
    private final Point COORDINATES = new Point(0, 0 );

    @BeforeEach
    void setUp() {
        square = new StickFigure(COORDINATES, INNER_SQUARE_SIZE, "111111111110");
    }

    /**
     * Que los sticks correspondan a las posiciones designadas al instanciarse
     * (ultimo dígito del string corresponde con el primer stick y primer dígito del string corresponde con último stick)
     */
    @Test
    void getSticks_SticksHaveBeenInitialized_SticksAreInTheirProperLocationByStringIndex() {
        assertFalse(square.getSticks().get(0).isPresent());
        for (int i = 1; i < square.getSticks().size(); i++)
            assertTrue(square.getSticks().get(i).isPresent());
    }

    /**
     * Que los sticks correspondan a las posiciones designadas al invocar changeState(String)
     * (ultimo dígito del string corresponde con el primer stick y primer dígito del string corresponde con último stick)
     */
    @Test
    void changeState_SticksHaveBeenInitialized_SticksAreInTheirProperLocationByStringIndex() {
        square.changeState("111111000000");
        for (int i = 0; i < 6; i++)
            assertFalse(square.getSticks().get(i).isPresent());
        for (int i = 6; i < square.getSticks().size(); i++)
            assertTrue(square.getSticks().get(i).isPresent());
    }

    /**
     * Que los sticks estén cada uno en su posición deseada al instanciar
     * (8 posiciones internas)
     */
    @Test
    void getSticks_SticksHaveBeenInitialized_SticksAreInTheirProperLocationByIndexAndCoordinates() {
        for (int i = 0; i < square.getSticks().size(); i++)
            assertEquals(getExpectedCoordinatesForStickAtIndexPosition(i), square.getSticks().get(i).getPosition());
    }

    private Point getExpectedCoordinatesForStickAtIndexPosition(int index) {

        Map<Integer, Point> POSITIONS = new HashMap<>();
        POSITIONS.put(0, square.getPosition());
        POSITIONS.put(1, new Point(square.getPosition().x + (square.getInnerSquareSize() * 2), square.getPosition().y));
        POSITIONS.put(2, new Point(square.getPosition().x + (square.getInnerSquareSize() * 4), square.getPosition().y));
        POSITIONS.put(3, new Point(square.getPosition().x, square.getPosition().y + (square.getInnerSquareSize() * 2)));
        POSITIONS.put(4, new Point(square.getPosition().x + (square.getInnerSquareSize() * 2), square.getPosition().y + (square.getInnerSquareSize() * 2)));
        POSITIONS.put(5, new Point(square.getPosition().x + (square.getInnerSquareSize() * 4), square.getPosition().y + (square.getInnerSquareSize() * 2)));
        POSITIONS.put(6, new Point(square.getPosition().x, square.getPosition().y + (square.getInnerSquareSize() * 4)));
        POSITIONS.put(7, new Point(square.getPosition().x + (square.getInnerSquareSize() * 2), square.getPosition().y + (square.getInnerSquareSize() * 4)));

        switch (index) {
            case 0:
            case 2:
                return POSITIONS.get(0);
            case 1:
            case 3:
                return POSITIONS.get(1);
            case 4:
                return POSITIONS.get(2);
            case 5:
            case 7:
                return POSITIONS.get(3);
            case 6:
            case 8:
                return POSITIONS.get(4);
            case 9:
                return POSITIONS.get(5);
            case 10:
                return POSITIONS.get(6);
            case 11:
                return POSITIONS.get(7);
        }
        return null;
    }

    /**
     * Que los sticks estén cada uno en su posición deseada después de invocar changeState(String)
     * (8 posiciones internas)
     */
    @Test
    void changeState_SticksHaveBeenInitialized_SticksAreInTheirProperLocationByIndexAndCoordinates() {
        square.changeState("111111000000");

        for (int i = 0; i < square.getSticks().size(); i++)
            assertEquals(getExpectedCoordinatesForStickAtIndexPosition(i), square.getSticks().get(i).getPosition());
    }

    /**
     * Que los sticks estén cada uno en su posición deseada después de invocar setInnerSquareSize(int)
     * (8 posiciones internas)
     */
    @Test
    void setInnerSquareSize_SticksHaveBeenInitialized_SticksAreInTheirProperLocationByIndexAndCoordinates() {
        square.setInnerSquareSize(20);

        for (int i = 0; i < square.getSticks().size(); i++)
            assertEquals(getExpectedCoordinatesForStickAtIndexPosition(i), square.getSticks().get(i).getPosition());
    }

    /**
     * Que los sticks estén cada uno en su posición deseada después de invocar setPosition(Point)
     * (8 posiciones internas)
     */
    @Test
    void setPosition_SticksHaveBeenInitialized_SticksAreInTheirProperLocationByIndexAndCoordinates() {
        square.setPosition(new Point(20, 10));

        for (int i = 0; i < square.getSticks().size(); i++)
            assertEquals(getExpectedCoordinatesForStickAtIndexPosition(i), square.getSticks().get(i).getPosition());
    }

    /**
     * Que el ancho sea el esperado para cada stick después de una llamada a setInnerSquareSize(int)
     */
    @Test
    void setInnerSquareSize_SticksHaveBeenInitialized_EachStickHasItsExpectedWidth() {
        square.setInnerSquareSize(50);

        for (int i = 0; i < square.getSticks().size(); i++) {
            if (isVerticalStickAtIndexPosition(i)) {
                assertEquals(square.getInnerSquareSize(), square.getSticks().get(i).getWidth());
            } else {
                assertEquals(square.getInnerSquareSize() * 3, square.getSticks().get(i).getWidth());
            }
        }
    }

    private boolean isVerticalStickAtIndexPosition(int index) {
        return new ArrayList<>(Arrays.asList(2, 3, 4, 7, 8, 9)).contains(index);
    }

    /**
     * Que el alto sea el esperado para cada stick después de una llamada a setInnerSquareSize(int)
     */
    @Test
    void setInnerSquareSize_SticksHaveBeenInitialized_EachStickHasItsExpectedHeight() {
        square.setInnerSquareSize(50);

        for (int i = 0; i < square.getSticks().size(); i++) {
            if (isVerticalStickAtIndexPosition(i)) {
                assertEquals(square.getInnerSquareSize() * 3, square.getSticks().get(i).getHeight());
            } else {
                assertEquals(square.getInnerSquareSize(), square.getSticks().get(i).getHeight());
            }
        }
    }

}
