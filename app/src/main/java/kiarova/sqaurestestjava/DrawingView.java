package kiarova.sqaurestestjava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import kiarova.sqaurestestjava.squares.SquareManager;

public class DrawingView extends View implements WindowSizeProvider {
    private final Paint paint = new Paint();
    private final SquareManager squareManager = new SquareManager(this, paint);

    private float lastTouchX;
    private float lastTouchY;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getWindowWidth() {
        return getWidth();
    }

    @Override
    public int getWindowHeight() {
        return getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        squareManager.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                float x = event.getX();
                float y = event.getY();
                lastTouchX = x;
                lastTouchY = y;
                squareManager.onPointerDown(x, y);
                invalidate();
                performClick();
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                float x = event.getX();
                float y = event.getY();
                float deltaX = x - lastTouchX;
                float deltaY = y - lastTouchY;
                lastTouchX = x;
                lastTouchY = y;
                squareManager.onPointerMove(deltaX, deltaY);
                invalidate();
                return true;
            }
            case MotionEvent.ACTION_UP: {
                squareManager.onPointerUp();
                invalidate();
                return true;
            }
            default: {
                return super.onTouchEvent(event);
            }
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

}
