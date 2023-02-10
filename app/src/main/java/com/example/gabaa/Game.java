package com.example.gabaa;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
// Game Manages all the objects in the game and is repsonsible for updating all states and render all
public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private Context context;
    public Game(Context context) {
        super(context);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        this.context = context;
        gameLoop = new GameLoop(this, surfaceHolder);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUps(canvas);
        drawFPS(canvas);
    }

    public void drawUps(Canvas canvas){
        String averageUps = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.purple_200);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("Ups" + averageUps, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas){
        String averageUps = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.white);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("Fps" + averageUps, 100, 200, paint);
    }

    public void update() {
        //Updating the game
    }
}
