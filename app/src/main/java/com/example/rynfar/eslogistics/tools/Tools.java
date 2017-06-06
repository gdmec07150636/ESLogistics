package com.example.rynfar.eslogistics.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.Toast;

/**
 * Created by rynfar on 2017/5/26.
 */

public class Tools {

    public static void ShowShortToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ShowLongToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 使用RenderScript实现高斯模糊的算法
     *
     * @param bitmap
     * @return
     */
    public static Bitmap blur(Context context, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        //先压缩100倍
        matrix.setScale(0.1f, 0.1f);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(context);
        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);
        //Set the radius of the blur: 0 < radius <= 25
        blurScript.setRadius(25.0f);
        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);
        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);
        //recycle the original bitmap
        bitmap.recycle();
        //After finishing everything, we destroy the Renderscript.
        rs.destroy();
        return outBitmap;

    }

    /**
     * 压缩图片
     * @param bitmap
     * @param ScaleX
     * @param ScaleY
     */
    public static void MartixBitmap(Bitmap bitmap,float ScaleX,float ScaleY){
        Matrix matrix = new Matrix();
        matrix.setScale(ScaleX, ScaleY);
        if (bitmap.getWidth()>100&&bitmap.getWidth()>100){
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), matrix, true);
        }
    }

    /**
     * 压缩图片
     * @param bitmap
     */
    public static void MartixBitmap(Bitmap bitmap){
        Matrix matrix = new Matrix();
        matrix.setScale(0.1f,0.1f);
        if (bitmap.getWidth()>100&&bitmap.getWidth()>100){
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), matrix, true);
        }
    }

    /**
     * 使用RenderScript实现高斯模糊的算法
     *
     * @param bitmap
     * @return
     */
    public static Bitmap blur(Context context, Bitmap bitmap, float Radius) {
        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(context);
        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);
        //Set the radius of the blur: 0 < radius <= 25
        blurScript.setRadius(Radius);
        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);
        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);
        //recycle the original bitmap
        bitmap.recycle();
        //After finishing everything, we destroy the Renderscript.
        rs.destroy();
        return outBitmap;

    }
}
