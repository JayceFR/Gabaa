package com.example.gabaa;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class Player {

    private double positionX;
    public double positionY;
    public double radius;
    public Paint paint;
    public int display_height = 0;
    private double velocityX;
    public boolean colliding = false;
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

    public void update(Joystick joystick, ArrayList<tile_rects> tiles) {
        velocityX = joystick.getActuatorX()*max_speed;
        velocityY = joystick.getActuatorY()*max_speed;
        positionX += velocityX;
        if (!colliding){
            positionY += velocityY;
            positionY += 9.8;
        }


    }

    public String collision_test(ArrayList<tile_rects> tiles){
        float distance_between = 0.0f;
        ArrayList<tile_rects> hitlist = new ArrayList<tile_rects>();
        //colliding = false;
        for(tile_rects tile : tiles){
            distance_between = (float) Math.sqrt(Math.pow((tile.get_x() - positionX), 2) + Math.pow((tile.get_y() - positionY), 2));
            if (distance_between < 30.0f){
                //Colliding up
                positionY = tile.get_y() - 30.0f;
                colliding = true;
                hitlist.add(tile);
            }
        }
        return  hitlist.toString();
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
