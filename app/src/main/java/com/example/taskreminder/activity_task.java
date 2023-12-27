package com.example.taskreminder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class activity_task extends AppCompatActivity {

    private Button btnAddTask2;
    private Button btnDeleteTask;
    ListView taskListView ;
    private TaskBaseSQLite db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        init();

        db = new TaskBaseSQLite(this);

        ArrayList<Task> tasks = db.getAllTasks();

        ArrayAdapter<Task> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        taskListView.setAdapter(adapter);

        btnAddTask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(activity_task.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        btnDeleteTask.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(activity_task.this, DeleteTask.class);
                startActivity(intent2);
            }
        });

    }

    void init()
    {
        btnAddTask2 = findViewById(R.id.btnAddTask2);
        btnDeleteTask = findViewById(R.id.btnDeleteTask1);
        taskListView = findViewById(R.id.listViewTasks);
    }
}
