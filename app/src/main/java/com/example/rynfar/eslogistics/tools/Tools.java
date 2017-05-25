package com.example.rynfar.eslogistics.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by rynfar on 2017/5/26.
 */

public class Tools {
    public static void ShowShotToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    public static void ShowLongToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
