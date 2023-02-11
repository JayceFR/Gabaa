package com.example.gabaa;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Map {
    private Bitmap tile1;
    public Map(Context context){
        //loading all the tile_sets
        BitmapFactory.Options bitmap_options = new BitmapFactory.Options();
        bitmap_options.inScaled = false;
        tile1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile1, bitmap_options);
    }
    public void draw(Canvas canvas) {
        canvas.drawBitmap(tile1, 100, 200, null);
    }
}
