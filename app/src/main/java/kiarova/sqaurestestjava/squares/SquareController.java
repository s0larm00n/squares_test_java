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
    private final int size;

    public SquareController(Paint viewPaint, int x, int y, int squareSize) {
        paint = viewPaint;
        rect = new Rect(x, y, x + squareSize, y + squareSize);
        color = Color.rgb(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255),
                (int) (Math.random() * 255)
        );
        size = squareSize;
    }

    public void draw(Canvas canvas) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect, paint);
        if (highlighted) {
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            canvas.drawRect(rect, paint);
        }
    }

    public void setHighlighted(boolean value) {
        highlighted = value;
    }

    public boolean contains(int x, int y) {
        return rect.contains(x, y);
    }

    public void moveBy(int deltaX, int deltaY) {
        rect.offset(deltaX, deltaY);
    }

    public void ensureBorders(int width, int height) {
        if (rect.left < 0) {
            rect.set(0, rect.top, size, rect.bottom);
        }
        if (rect.right > width) {
            rect.set(width - size, rect.top, width, rect.bottom);
        }
        if (rect.top < 0) {
            rect.set(rect.left, 0, rect.right, size);
        }
        if (rect.bottom > height) {
            rect.set(rect.left, height - size, rect.right, height);
        }
    }

}
