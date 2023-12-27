package com.example.taskreminder;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Button btnAddTask;
    private Button NbrTasks ;
    private Button btnConsulter;

    private DatePicker dpDate;
    TaskBaseSQLite db ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new TaskBaseSQLite(this);

        init();



        btnAddTask.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                onAddTaskClick();
            }
        });



        btnConsulter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onConsultTasks();
            }
        });



        NbrTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCountTasks();
            }
        });



        findViewById(android.R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });
    }





    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }





    private void init() {
        editTextTitle = findViewById(R.id.etTitle);
        editTextDescription = findViewById(R.id.etDescription);
        btnAddTask = findViewById(R.id.btnAddTask1);
        btnConsulter = findViewById(R.id.btnConsulter);
        NbrTasks= findViewById(R.id.NbrTasks);
        dpDate = findViewById(R.id.dpDate);
    }


    private void  onConsultTasks()
    {
        Intent intent = new Intent(MainActivity.this, activity_task.class);
        startActivity(intent);
    }

    private void onCountTasks()
    {
        long all_taks = db.getTasksCount();
        Toast.makeText(MainActivity.this, all_taks+" Taches a réalisé", Toast.LENGTH_SHORT).show();
    }
    private void onAddTaskClick()
    {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int year = dpDate.getYear();
        int month = dpDate.getMonth();
        int day = dpDate.getDayOfMonth();

        String monthString;
        switch (month) {
            case 0:
                monthString = "January";
                break;
            case 1:
                monthString = "February";
                break;
            case 2:
                monthString = "March";
                break;
            case 3:
                monthString = "April";
                break;
            case 4:
                monthString = "May";
                break;
            case 5:
                monthString = "June";
                break;
            case 6:
                monthString = "July";
                break;
            case 7:
                monthString = "August";
                break;
            case 8:
                monthString = "September";
                break;
            case 9:
                monthString = "October";
                break;
            case 10:
                monthString = "November";
                break;
            case 11:
                monthString = "December";
                break;
            default:
                monthString = "Invalid month";
                break;
        }
        String date = String.valueOf(day + " "+monthString+" "+ year);

        boolean verifTitle = false ;
        boolean verifDescrition = false;
        boolean verifDate = false ;

        if(!editTextTitle.getText().toString().isEmpty())
            verifTitle = true;
        else
            Toast.makeText(MainActivity.this, "Veuillez remplir le titre !",Toast.LENGTH_LONG).show();

        if(!editTextDescription.getText().toString().isEmpty())
            verifDescrition = true;
        else
            Toast.makeText(MainActivity.this, "Veuillez remplir la Description !",Toast.LENGTH_LONG).show();

        if(date != "")
            verifDate = true;
        else
            Toast.makeText(MainActivity.this, "Veuillez remplir la Date !",Toast.LENGTH_LONG).show();
        if(verifTitle && verifDescrition && verifDate)
        {
            Task newTask = new Task(title, description, date);
            boolean res = db.insertTask(newTask);
            if (res) {
                Toast.makeText(MainActivity.this, "Tâche ajoutée avec succès", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(MainActivity.this, activity_task.class);
            startActivity(intent);
        }
    }
}