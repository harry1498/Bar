package com.itb.itbbar.Utils;

import android.content.Context;
import android.widget.Toast;

public class AppUtils {

    Context context;

    public AppUtils(Context context) {
        this.context = context;
    }

    public static void showToast(Context context, String message){
        Toast.makeText(context, "CLicked: "+message, Toast.LENGTH_SHORT).show();

    }
}
