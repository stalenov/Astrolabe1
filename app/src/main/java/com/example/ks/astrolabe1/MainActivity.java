package com.example.ks.astrolabe1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

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

    public void onStart() {
        super.onStart();
        Log.d(TAG, "ONSTART");
        //for (int i = 0; i < 10; i++) {
            //Log.d(TAG, String.valueOf(i));
            // Запрос количеств обращений по каждой из категорий
            String httpResponse = null;
            GetHTTP gt = new GetHTTP();
            gt.execute("getTasksInfo.php");
            String tasksWOExecNUM, tasksForMeNUM, tasksFromMeNUM;
            Log.d(TAG,"HTTP");
            try {
                httpResponse = gt.get();
                JSONObject jResponse = new JSONObject(httpResponse);
                tasksWOExecNUM = jResponse.getString("taskWOexec");
                btnWithoutExec.setText("НЕРАСПРЕДЕЛЕННЫЕ ОБРАЩЕНИЯ (" + tasksWOExecNUM + ")");
                tasksForMeNUM = jResponse.getString("tasksForMe");
                btnWhereYouExec.setText("НАЗНАЧЕННЫЕ МНЕ ОБРАЩЕНИЯ (" + tasksForMeNUM + ")");
                tasksFromMeNUM = jResponse.getString("tasksFromMe");
                btnWhereYouUser.setText("СОЗДАННЫЕ МНОЮ ОБРАЩЕНИЯ (" + tasksFromMeNUM + ")");

                //Log.d(TAG, "HTTP data: " + tasksWOExecNUM + " " + tasksForMeNUM + " " + tasksFromMeNUM);

                //System.out.println(httpResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }

        //}
    }


}
