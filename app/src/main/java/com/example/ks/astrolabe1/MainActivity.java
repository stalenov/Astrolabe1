package com.example.ks.astrolabe1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnWithoutExec;
    Button btnWhereYouExec;
    Button btnWhereYouUser;
    Button btnNewTask;
    final String TAG = "myLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Листенеры для кнопок
        btnWithoutExec = (Button)findViewById(R.id.btnWithoutExec);
        btnWhereYouExec = (Button)findViewById(R.id.btnWhereYouExec);
        btnWhereYouUser = (Button)findViewById(R.id.btnWhereYouUser);
        btnNewTask = (Button)findViewById(R.id.btnNewTask);
        btnWithoutExec.setOnClickListener(this);
        btnWhereYouExec.setOnClickListener(this);
        btnWhereYouUser.setOnClickListener(this);
        btnNewTask.setOnClickListener(this);
        Log.d(TAG,"Activity created");
    }

    // создание меню
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "MENU CREATED");
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Toast.makeText(this,String.valueOf(item.getItemId()),Toast.LENGTH_SHORT).show();
        Log.d(TAG, "MENU ITEM SELECTED");
        return super.onOptionsItemSelected(item);
    }

    // Обработка кнопок активити
    @Override
    public void onClick(View v) {
        Log.d(TAG, String.valueOf(v.getId()));
        Intent intent;
        switch (v.getId()){
            case R.id.btnWithoutExec:
                Log.d(TAG, "without executors");
                intent = new Intent(this, TasksWoExecs.class);
                startActivity(intent);
                break;
            case R.id.btnWhereYouExec:
                Log.d(TAG, "where you executor");
                intent = new Intent(this, TasksForMe.class);
                startActivity(intent);
                break;
            case R.id.btnWhereYouUser:
                Log.d(TAG, "where you user");
                intent = new Intent(this, TasksFromMe.class);
                startActivity(intent);
                break;
            case R.id.btnNewTask:
                Log.d(TAG, "new task");
                intent = new Intent(this, NewTask.class);
                startActivity(intent);
                break;
        }
    }
}
