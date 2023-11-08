package kiarova.sqaurestestjava.squares;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class SquareController {
    private final Rect rect;
    private final int color;
    private boolean highlighted = false;
    private final Paint paint;


    public SquareController(Paint viewPaint, int x, int y, int size) {
        paint = viewPaint;
        rect = new Rect(x, y, x + size, y + size);
        color = Color.rgb(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255),
                (int) (Math.random() * 255)
        );
    }

    public void draw(Canvas canvas) {
        paint.setColor(color);
        canvas.drawRect(rect, paint);
    }

    public void setHighlighted(boolean value) {
        highlighted = value;
    }

}
