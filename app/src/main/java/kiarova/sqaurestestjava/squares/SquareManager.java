package kiarova.sqaurestestjava.squares;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kiarova.sqaurestestjava.WindowSizeProvider;

public class SquareManager {

    private final Paint paint;
    private final List<SquareController> squares = new ArrayList<>();
    private SquareController focusedSquare = null;
    private final WindowSizeProvider windowSizeProvider;

    public SquareManager(WindowSizeProvider sizeProvider, Paint viewPaint) {
        windowSizeProvider = sizeProvider;
        paint = viewPaint;
    }

    public void draw(Canvas canvas) {
        for (int i = 0; i < squares.size(); i++) {
            squares.get(i).draw(canvas);
        }
    }

    public void onPointerDown(float touchX, float touchY) {
        focusedSquare = getSquareAt(touchX, touchY);
    }

    public void onPointerMove(float deltaX, float deltaY) {
        if (focusedSquare != null) {
            focusedSquare.moveBy((int) deltaX, (int) deltaY);
        }
    }

    public void onPointerUp() {
        focusedSquare = null;
    }

    private SquareController addSquare(float touchX, float touchY, int size) {
        SquareController newSquare = new SquareController(paint, (int) (touchX - size / 2), (int) (touchY - size / 2), size);
        if (getTopSquare() != null) {
            getTopSquare().setHighlighted(false);
        }
        newSquare.setHighlighted(true);
        squares.add(newSquare);
        return newSquare;
    }

    private SquareController getTopSquare() {
        if (squares.isEmpty()) {
            return null;
        }
        return squares.get(squares.size() - 1);
    }

    private SquareController getSquareAt(float touchX, float touchY) {
        SquareController detectedSquare = null;
        for (int i = squares.size() - 1; i >= 0; i--) {
            if (squares.get(i).contains((int) touchX, (int) touchY)) {
                detectedSquare = squares.get(i);
                Objects.requireNonNull(getTopSquare()).setHighlighted(false);
                detectedSquare.setHighlighted(true);
                squares.remove(i);
                squares.add(detectedSquare);
                break;
            }
        }
        if (detectedSquare == null) {
            detectedSquare = addSquare(
                    touchX,
                    touchY,
                    (Math.min(windowSizeProvider.getWindowWidth(), windowSizeProvider.getWindowHeight())) / 10
            );
        }
        return detectedSquare;
    }

}
