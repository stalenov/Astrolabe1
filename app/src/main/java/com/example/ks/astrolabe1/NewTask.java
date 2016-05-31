package com.example.ks.astrolabe1;

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewTask extends AppCompatActivity implements View.OnClickListener {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);

        Button btnNewTask = (Button)findViewById(R.id.btnSaveTask);
        btnNewTask.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Log.d("MyLog", "onClickListener");

        switch (v.getId()){
            case R.id.btnSaveTask:
                Log.d("MyLog", "btnSaveTask " + v.getId());
                Toast.makeText(this, "Эта функция еще действует и не планируется к разработке. Прекратите жать на кнопки!", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
