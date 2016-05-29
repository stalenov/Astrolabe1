package com.example.ks.astrolabe1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TasksForMe extends AppCompatActivity {
    TextView contentView;

    final String TAG = "MyLog";

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_for_me);

        String httpResponse = null;
        GetHTTP gt = new GetHTTP();
        gt.execute("getTasks.php");
        try {
            httpResponse = gt.get();
            //System.out.println(httpResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO реализовать сохранение связи с AsyncTask при повороте

        // Парсим заявки в ArrayList
        ArrayList<HashMap<String, String>> alTasks = new ArrayList<>();

        try {
            JSONObject jResponse = new JSONObject(httpResponse);
            JSONObject jTaskList = jResponse.getJSONObject("tasksList");
            JSONArray jTasks = jTaskList.getJSONArray("tasks");


            for (int i = 0; i < jTasks.length(); i++) {
                JSONObject task = jTasks.getJSONObject(i);
                HashMap<String, String> hmTask = new HashMap<>();
                hmTask.put("id", task.getString("id_task"));
                hmTask.put("text", task.getString("task_text"));

                alTasks.add(hmTask);
                Log.d(TAG, task.getString("id_task"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //ListView списка обращений
        ListView lvTasks = (ListView) findViewById(R.id.lvTasks);
        SimpleAdapter adapter = new SimpleAdapter(this, alTasks, android.R.layout.simple_list_item_2,
                new String[]{"id", "text"},
                new int[]{android.R.id.text1, android.R.id.text2});


        lvTasks.setAdapter(adapter);
        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG-SHMAG", "" + id);
            }
        });


        registerForContextMenu(lvTasks);


    }

    // Контекстное меню
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.tasks_for_me_menu,menu);
    }

    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.userInfo:
                Log.d(TAG,"userInfo");
                Toast.makeText(this, "Пользователь - моральный урод, раз тревожит тебя обращениями", Toast.LENGTH_LONG).show();
                break;
            case R.id.pochki:
                Log.d(TAG,"pochki");
                Toast.makeText(this, "Пользователь был стукнут по почкам и корчится от боли на полу", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}