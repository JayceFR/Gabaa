package com.example.gabaa;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Enemy {
    public final float speed;
    public final Paint paint;
    public float[] spawn_pos = new float[2];
    public boolean shooting_right = false;
    public Enemy(Context context, float spawn_position_x, float spawn_position_y, boolean shooting_right, float speed){
        this.spawn_pos[0] = spawn_position_x;
        this.spawn_pos[1] = spawn_position_y;
        this.shooting_right = shooting_right;
        this.speed = speed;

        //Paint
        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.enemy);
        paint.setColor(color);
    }
    public void update(){
        if(this.shooting_right){
            spawn_pos[0] += speed;
        }
        else{
            spawn_pos[1] += speed;
        }
    }
    public void draw(Canvas canvas){
        canvas.drawCircle(spawn_pos[0], spawn_pos[1], 40.0f, paint);
    }
    public float get_x(){
        return spawn_pos[0];
    }
    public float get_y(){
        return spawn_pos[1];
    }
}


