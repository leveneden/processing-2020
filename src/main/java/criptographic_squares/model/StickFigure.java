package criptographic_squares.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

@Data
@AllArgsConstructor
public final class StickFigure implements Drawable, Updatable {

    private Point position;
    private int innerSquareSize;
    private List<Stick> sticks = new ArrayList<>();

    public StickFigure(Point position, int innerSquareSize, String sticksPresent) {
        this.position = position;
        this.innerSquareSize = innerSquareSize;
        if (isValid(sticksPresent)) {
            initializeSticks(sticksPresent);
        } else {
            throwInputMissmatchException();
        }
    }

    public void setPosition(Point position) {
        this.position = position;
        update();
    }

    public void setInnerSquareSize(int innerSquareSize) {
        this.innerSquareSize = innerSquareSize;
        update();
    }

    public void setSticks(List<Stick> sticks) {
        if (sticks.size() == 12) {
            this.sticks = sticks;
            update();
        } else {
            throwInputMissmatchException();
        }
    }

    public void changeState(String binaryString) {
        if (isValid(binaryString)) {
            updateSticks(binaryString);
        } else {
            throwInputMissmatchException();
        }
    }

    @Override
    public void update() {
        updateSticks();
    }

    private void initializeSticks(String binaryString) {
        String reversedBinaryString = reverseString(binaryString);
        for (int i = 0; i < reversedBinaryString.length(); i++) {
            String currentCharacter = reversedBinaryString.substring(i, i + 1);
            sticks.add(
                    new Stick(
                            getPointForStickIndex(i),
                            getWidthForStickIndex(i),
                            getHeightForStickIndex(i),
                            currentCharacter.equals("1")
                    )
            );
        }
    }

    private boolean isValid(String binaryString) {
        if (binaryString.length() != 12) return false;
        for (int i = 0; i < binaryString.length(); i++) {
            String currentCharacter = binaryString.substring(i, i + 1);
            if (!currentCharacter.equals("0") && !currentCharacter.equals("1")) return false;
        }
        return true;
    }

    private String reverseString(String stringToReverse) {
        String reversedString = "";
        for (int i = stringToReverse.length() - 1; i >= 0; i--) {
            reversedString += stringToReverse.charAt(i);
        }
        return reversedString;
    }

    private Point getPointForStickIndex(int index) {
        if (index == 0 || index == 2) {
            // position 0
            return new Point(position.x, position.y);
        } else if (index == 1 || index == 3) {
            // position 1
            return new Point(position.x + (innerSquareSize * 2), position.y);
        } else if (index == 4) {
            // position 2
            return new Point(position.x + (innerSquareSize * 4), position.y);
        } else if (index == 5 || index == 7) {
            // position 3
            return new Point(position.x, position.y + (innerSquareSize * 2));
        } else if (index == 6 || index == 8) {
            // position 4
            return new Point(position.x + (innerSquareSize * 2), position.y + (innerSquareSize * 2));
        } else if (index == 9) {
            // position 5
            return new Point(position.x + (innerSquareSize * 4), position.y + (innerSquareSize * 2));
        } else if (index == 10) {
            // position 6
            return new Point(position.x, position.y + (innerSquareSize * 4));
        } else if (index == 11) {
            // position 7
            return new Point(position.x + (innerSquareSize * 2), position.y + (innerSquareSize * 4));
        }
        return null;
    }

    private int getWidthForStickIndex(int index) {
        List<Integer> horizontalStickIndexes = new ArrayList<>(Arrays.asList(0, 1, 5, 6, 10, 11));
        List<Integer> verticalStickIndexes = new ArrayList<>(Arrays.asList(2, 3, 4, 7, 8, 9));
        if (horizontalStickIndexes.contains(index)) {
            return innerSquareSize * 3;
        } else if (verticalStickIndexes.contains(index)) {
            return innerSquareSize;
        }
        return -1;
    }

    private int getHeightForStickIndex(int index) {
        List<Integer> horizontalStickIndexes = new ArrayList<>(Arrays.asList(0, 1, 5, 6, 10, 11));
        List<Integer> verticalStickIndexes = new ArrayList<>(Arrays.asList(2, 3, 4, 7, 8, 9));
        if (horizontalStickIndexes.contains(index)) {
            return innerSquareSize;
        } else if (verticalStickIndexes.contains(index)) {
            return innerSquareSize * 3;
        }
        return -1;
    }

    private void updateSticks() {
        String currentState = "";
        for (Stick stick : sticks)
            currentState += stick.isPresent() ? "1" : "0";
        updateSticks(currentState);
    }

    private void updateSticks(String binaryString) {
        sticks = new ArrayList<>();
        initializeSticks(binaryString);
    }

    private void throwInputMissmatchException() {
        throw new InputMismatchException(
                "The input for this method should be of length 12 and composed of only \"0\"s and \"1\"s.");
    }
}
