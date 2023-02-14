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
    private double[] movement;

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
        this.movement = new double[]{0.0d, 0.0d};
        velocityX = joystick.getActuatorX()*max_speed;
        velocityY = joystick.getActuatorY()*max_speed;
        movement[0] += velocityX;

        movement[1] += 9.81d;


        /*positionX += velocityX;
        positionY += velocityY;
        positionY += 9.8;*/

        if (tiles!=null){
            collision_checker(tiles);
            //this.collision_test(tiles);
        }
    }

    public void collision_checker(ArrayList<tile_rects> tiles) {
        positionX += movement[0];
        ArrayList<tile_rects> hit_list = collision_test(tiles);
        for(tile_rects tile : hit_list){
            if (movement[0] > 0.0d) {
                //Colliding right...
                positionX = tile.get_x() - 30.0f;
            }
            if(movement[0] < 0.0d){
                //Colliding left
                positionX = tile.get_x() + 94.0f;
            }
        }
        positionY += movement[1];
        hit_list.clear();
        hit_list = collision_test(tiles);
        colliding = false;
        for(tile_rects tile : hit_list){
            if (movement[1] > 0.0d){
                //Colliding down
                double to_add = 30 - (tile.get_y() - positionY);
                colliding = true;
                positionY -= to_add;
            }
            if (movement[1] < 0.0d) {
                //Colliding up
                positionY = tile.get_y() + 94.0d;
            }
        }
    }

    public ArrayList<tile_rects> collision_test(ArrayList<tile_rects> tiles){
        float distance_between = 0.0f;
        ArrayList<tile_rects> hitlist = new ArrayList<tile_rects>();
        for(tile_rects tile : tiles){
            distance_between = (float) Math.sqrt(Math.pow((tile.get_x() - positionX + 32), 2) + Math.pow((tile.get_y() - positionY + 32), 2));
            if (distance_between < 62){
                hitlist.add(tile);
            }
        }
        return  hitlist;
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
