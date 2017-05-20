package com.example.rynfar.eslogistics.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

public class Blur {
  
    public static void blur(Context context, Bitmap bkg, View view) {
  
        float radius = 4; //模糊半径设置  
        float scaleFactor = 8;//由于是要模糊，没必要高清下模糊，低清模糊就可以了，压缩8倍。  
  
        Bitmap overlay = Bitmap.createBitmap((int)(view.getMeasuredWidth()/scaleFactor), (int)(view.getMeasuredHeight()/scaleFactor), Bitmap.Config.ARGB_8888);    
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-view.getLeft()/scaleFactor, -view.getTop()/scaleFactor);    
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);    
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);    
        canvas.drawBitmap(bkg, 0, 0, paint);    
        overlay = FastBlur.doBlur(overlay, (int)radius, true);    
        view.setBackground(new BitmapDrawable(context.getResources(), overlay));
        //view.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }     
}