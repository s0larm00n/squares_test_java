package kiarova.sqaurestestjava.squares;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

public class SquareManager {

    private final Paint paint;
    private final List<SquareController> squares = new ArrayList<>();

    public SquareManager(Paint viewPaint) {
        paint = viewPaint;
    }

    public void draw(Canvas canvas) {
        for (int i = 0; i < squares.size(); i++) {
            squares.get(i).draw(canvas);
        }
    }

    public void addSquare(float touchX, float touchY, int size) {
        squares.add(new SquareController(paint, (int) (touchX - size / 2), (int) (touchY - size / 2), size));
    }

}
