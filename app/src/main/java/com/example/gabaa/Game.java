package com.example.gabaa;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

// Game Manages all the objects in the game and is repsonsible for updating all states and render all
public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final Map map;
    private final Enemy enemy;
    private final Joystick joystick;
    private long wave_last_update;
    private long wave_cooldown;
    private long game_time;
    private GameLoop gameLoop;
    private Context context;
    public int display_height = 0;
    public ArrayList<Wave> waves = new ArrayList<Wave>();
    public int display_width = 0;
    public Game(Context context, int height, int width) {
        super(context);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        this.context = context;
        display_height = height;
        display_width = width;
        gameLoop = new GameLoop(this, surfaceHolder);
        joystick = new Joystick(275,700,70,40);
        player = new Player(getContext(), display_width/2, display_height - 80, 30);
        map = new Map(getContext());
        enemy = new Enemy(getContext(), 200,250, true, 6.0f);
        //enemies.add(new Enemy(getContext(), 200.0f,550.0f, true, 6.0f));
        //wave = new Wave(getContext(), width, height, 0);
        game_time = 0;
        wave_cooldown = 200;
        wave_last_update = 0;
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(joystick.isPressed((double)event.getX(), (double)event.getY())){
                    joystick.setIsPressed(true);
                }
                //player.setPosition((double)event.getX(), (double)event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                if(joystick.getIsPressed()){
                    joystick.setActuator((double)event.getX(), (double)event.getY());
                }
                //player.setPosition((double)event.getX(), (double)event.getY());
                return true;
            case MotionEvent.ACTION_UP:
                joystick.setIsPressed(false);
                joystick.resetActuator();
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        if(gameLoop.getState().equals(Thread.State.TERMINATED)){
            gameLoop = new GameLoop(this, surfaceHolder);
        }
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
        player.draw(canvas);
        enemy.draw(canvas);
        joystick.draw(canvas);
        map.draw(canvas, display_height, display_width);
        for (int i = 0; i < waves.size(); i++){
            waves.get(i).update(canvas, player.get_x(), player.get_y());
            if (!waves.get(i).are_u_alive()){
                waves.remove(i);
                i = i-1;
            }
        }
        //wave.update(canvas);
        //map.draw(canvas);
    }

    public void drawUps(Canvas canvas){
        String averageUps = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.purple_200);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("Ups" + averageUps, 100, 100, paint);
        canvas.drawText("game_time" + new Long(game_time).toString(), 100, 400, paint);
        canvas.drawText(map.get_array(), 100, 600, paint);
        canvas.drawText(new Integer(display_height).toString() + " " + new Integer(display_width).toString(), 100, 500, paint );
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
        //Updating the game running at 30fps
        player.update(joystick);
        enemy.update();
        joystick.update();
        game_time += 1;
        if(game_time - wave_last_update > wave_cooldown){
            //Create a wave
            waves.add(new Wave(getContext(), display_width, display_height, 0));
            wave_last_update = game_time;
        }
    }
}
