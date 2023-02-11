package com.example.gabaa;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Joystick {
    private  Paint inner_circle_paint;
    private Paint outer_circle_paint;
    private int inner_circle_radius;
    private  int outer_circle_radius;
    private int inner_circle_pos_y;
    private int inner_circle_pos_x;
    private  int outer_circle_pos_y;
    private int outer_circle_pos_x;
    private double joystickCenterToTouchDistance;
    private boolean ispressed;
    public double actuatorX;
    public double actuatorY;

    public Joystick(int centerPositionX, int centerPositionY, int outer_circle_radius, int inner_circle_radius){
        outer_circle_pos_x = centerPositionX;
        outer_circle_pos_y = centerPositionY;
        inner_circle_pos_x = centerPositionX;
        inner_circle_pos_y = centerPositionY;

        this.outer_circle_radius = outer_circle_radius;
        this.inner_circle_radius = inner_circle_radius;

        outer_circle_paint = new Paint();
        outer_circle_paint.setColor(Color.GRAY);
        outer_circle_paint.setStyle(Paint.Style.FILL_AND_STROKE);

        inner_circle_paint = new Paint();
        inner_circle_paint.setColor(Color.BLUE);
        inner_circle_paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }
    public void draw(Canvas canvas) {
        canvas.drawCircle(outer_circle_pos_x, outer_circle_pos_y, outer_circle_radius, outer_circle_paint);
        canvas.drawCircle(inner_circle_pos_x, inner_circle_pos_y, inner_circle_radius, inner_circle_paint);
    }

    public boolean isPressed(double x, double y) {
        joystickCenterToTouchDistance = Math.sqrt(Math.pow(outer_circle_pos_x- x, 2) + Math.pow(outer_circle_pos_y - y, 2));
        return joystickCenterToTouchDistance < outer_circle_radius;
    }

    public void setIsPressed(boolean b) {
        this.ispressed = b;
    }

    public boolean getIsPressed(){
        return this.ispressed;
    }

    public void update(){
        updateInnerCirclePostition();
    }

    public void updateInnerCirclePostition(){
        inner_circle_pos_x = (int)(outer_circle_pos_x + actuatorX * outer_circle_radius);
        inner_circle_pos_y = (int)(outer_circle_pos_y + actuatorY * outer_circle_radius);
    }

    public void setActuator(double x, double y) {
        double deltaX = x - outer_circle_pos_x;
        double deltaY = y - outer_circle_pos_y;
        double deltaDistance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        if(deltaDistance < outer_circle_radius){
            actuatorX = deltaX/outer_circle_radius;
            actuatorY = deltaY/outer_circle_radius;
        }
        else{
            actuatorX = deltaX/deltaDistance;
            actuatorY = deltaY/deltaDistance;
        }
    }

    public void resetActuator() {
        actuatorX = 0.0;
        actuatorY = 0.0;
    }

    public double getActuatorX() {
        return actuatorX;
    }
    public double getActuatorY() {
        return actuatorY;
    }
}
