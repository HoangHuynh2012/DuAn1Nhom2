package com.example.duan1.customer.Login;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.example.duan1.R;

public class Loading {
    Activity activity;
    AlertDialog dialog;
    public Loading(Activity myActivity){
        activity = myActivity;
    }
    public void startLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.loading_layout, null);
        builder.setCancelable(true);
        builder.setView(view);
        dialog = builder.create();
        dialog.show();

    }
    public void dismissDialog(){
        dialog.dismiss();
    }
}
