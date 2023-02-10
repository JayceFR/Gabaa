package com.example.gabaa;

import android.graphics.Canvas;
import android.view.Surface;
import android.view.SurfaceHolder;

import java.security.KeyStore;
import java.util.Observer;

public class GameLoop extends Thread {
    private static final double MAXUPS = 30.0;
    public static final double UPS_PERIOD = (1E+3/MAXUPS);
    public boolean isRunning = false;
    private SurfaceHolder surfaceHolder;
    private Game game;
    private double averageUPS;
    private double averageFPS;

    public GameLoop(Game game, SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
        this.game = game;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageUPS;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();

        int update_count = 0;
        int frame_count = 0;

        long startTime;
        long elapsedTime;
        long sleepTime;

        //Game Loop
        startTime = System.currentTimeMillis();
        Canvas canvas = null;
        while (isRunning){
            try{
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    game.update();
                    update_count++;
                    game.draw(canvas);
                }
            }
            catch(IllegalArgumentException e){
                e.printStackTrace();
            }
            finally {
                if (canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frame_count++;
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long)(update_count * UPS_PERIOD - elapsedTime);
            if (sleepTime > 0){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (sleepTime < 0 && update_count < MAXUPS-1){
                game.update();
                update_count++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long)(update_count * UPS_PERIOD - elapsedTime);
            }

            elapsedTime = System.currentTimeMillis() - startTime;
            if(elapsedTime >= 1000){
                averageUPS = update_count / (1E-3 * elapsedTime);
                averageFPS = frame_count / (1E-3 * elapsedTime);
                update_count = 0;
                frame_count = 0;
                startTime = System.currentTimeMillis();
            }

        }
    }
}
