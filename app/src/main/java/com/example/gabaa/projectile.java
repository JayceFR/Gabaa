package com.example.gabaa;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class projectile {
    private final Paint paint;
    public double x;
    public double y;
    public double actuator_x;
    public double actuator_y;
    public double max_speed;
    public boolean alive = false;
    public projectile(double start_x, double start_y, double actuator_x, double actuator_y){
        this.x = start_x;
        this.y = start_y;
        this.actuator_x = actuator_x;
        this.actuator_y = actuator_y;
        max_speed = 50.0d;
        paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    public void update(){
        if (alive){
            x += actuator_x*max_speed;
            y += actuator_y*max_speed;
        }

    }

    public void draw(Canvas canvas){
        if (alive){
            canvas.drawCircle((float) x, (float) y, 10, paint );
        }
    }

    public void set_alive(boolean choice){
        alive = choice;
    }

}
