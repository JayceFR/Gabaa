package com.example.gabaa;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import androidx.appcompat.view.menu.MenuBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Map {
    private final char[][] mymap;
    public ArrayList<Bitmap> tile_images = new ArrayList<Bitmap>();
    public int[] tile_locs;
    public int y = 0;
    //private Bitmap tile1;
    public Map(Context context){
        //loading all the tile_sets
        BitmapFactory.Options bitmap_options = new BitmapFactory.Options();
        bitmap_options.inScaled = false;
        tile_locs = new int[]{R.drawable.tile1, R.drawable.tile2, R.drawable.tile3, R.drawable.tile4, R.drawable.tile5, R.drawable.tile6, R.drawable.tile7, R.drawable.tile8, R.drawable.tile9};
        //tile1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile1, bitmap_options);
        for (int i = 0; i<tile_locs.length; i++){
            tile_images.add(BitmapFactory.decodeResource(context.getResources(), tile_locs[i], bitmap_options));
        }
        //mymap = new char[][]{{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'}, {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'}, {'1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0'}, {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}, {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}};
        mymap = new char[][]{{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},{'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},{'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}};

    }
    public ArrayList<tile_rects> draw(Canvas canvas, int height, int width) {
        ArrayList<tile_rects> tiles = new ArrayList<tile_rects>();
        y = (int) height  - (mymap.length * 64);
        for(int row = 0; row < mymap.length; row++){
            for(int element = 0; element<mymap[row].length; element++){
                if (mymap[row][element] != '0'){
                    canvas.drawBitmap(tile_images.get(Integer.parseInt(String.valueOf(mymap[row][element]))-1), element * 64, y + (row * 64), null);
                }
                if (mymap[row][element] != '0'){
                    tiles.add(new tile_rects(element * 64, y + (row * 64)));
                }
            }
        }
        return tiles;

    }
    public String get_array(){
        //return Arrays.deepToString(mymap);
        return new Integer(y).toString();
    }
}
