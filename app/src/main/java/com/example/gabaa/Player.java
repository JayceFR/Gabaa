package com.example.gabaa;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {

    private double positionX;
    public double positionY;
    public double radius;
    public Paint paint;
    public int display_height = 0;
    private double velocityX;
    private double velocityY;
    private double max_speed = 10.0d;

    public Player(Context context, double positionX, double positionY, double radius){
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;


        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle( (float)positionX, (float)positionY, (float)radius, paint);
    }

    public void update(Joystick joystick) {
        velocityX = joystick.getActuatorX()*max_speed;
        velocityY = joystick.getActuatorY()*max_speed;
        positionX += velocityX;
        positionY += velocityY;
    }

    public void setPosition(double x, double y) {
        positionX = x;
        //positionY = y;
    }
    public float get_x(){
        return (float)positionX;
    }
    public float get_y(){
        return (float) positionY;
    }
}
