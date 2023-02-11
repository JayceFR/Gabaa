package com.example.gabaa;

import android.content.Context;
import android.graphics.Canvas;
import android.icu.text.Edits;

import java.util.ArrayList;
import java.util.Iterator;

public class Wave {
    private final int width;
    private final int height;
    private final long current_pos;
    private final double speed;
    public boolean alive = true;

    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public Wave(Context context, int width, int height, long current_pos){
        this.width = width;
        this.height = height;
        this.current_pos = current_pos;
        this.speed = Math.floor(Math.random()*20);
        int choice = (int)Math.floor(Math.random()*2);
        int number_of_bullets = (int)Math.floor(Math.random()*(10 + current_pos));
        boolean shooting_right = false;
        alive = true;
        if(choice == 0){
            shooting_right = true;
        }
        for(int x = 0; x<=number_of_bullets; x++) {
            if (shooting_right) {
                enemies.add(new Enemy(context, 0.0f, (float) Math.floor(Math.random() * height), shooting_right, (float) speed));
            } else {
                enemies.add(new Enemy(context, (float) Math.floor(Math.random() * width), 0.0f, shooting_right, (float) speed));
            }
        }

    }
    public void update(Canvas canvas, float player_x, float player_y){
        float distance_between = 0.0f;
        for(int i = 0; i<enemies.size();i++){
            enemies.get(i).update();
            enemies.get(i).draw(canvas);
            if(to_destroy()){
                alive = false;
            }
            distance_between = (float) Math.sqrt(Math.pow((enemies.get(i).get_x() - player_x), 2) + Math.pow((enemies.get(i).get_y() - player_y), 2));
            if(distance_between < 70.0f){
                //Collision
                enemies.remove(i);
            }

        }
    }

    public boolean to_destroy(){
        boolean destroy = true;
        for(Enemy e: enemies){
            if(e.get_x() < width && e.get_y() < height){
                destroy = false;
            }
        }
        return destroy;
    }

    public boolean are_u_alive(){
        return alive;
    }

}
