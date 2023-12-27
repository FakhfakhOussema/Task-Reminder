package com.example.taskreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class DeleteTask extends AppCompatActivity {
    private Button btnRetour;
    private Button btnDeleteTask;
    private EditText etTitleSupp ;
    TaskBaseSQLite db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_task);

        init();

        db = new TaskBaseSQLite(this);

        findViewById(android.R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });

        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeleteTask.this, activity_task.class);
                startActivity(intent);
            }
        });


        btnDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                boolean verifTask = false ;
                if(!etTitleSupp.getText().toString().isEmpty())
                    verifTask = true;
                else
                    Toast.makeText(DeleteTask.this, "Veuillez remplir le titre !",Toast.LENGTH_LONG).show();

                if(verifTask)
                {
                    String task = etTitleSupp.getText().toString();
                    Boolean verif =db.deleteTask(task);
                    if(verif ==false)
                        Toast.makeText(DeleteTask.this, "Cette tâche n'existe pas", Toast.LENGTH_SHORT).show();
                    else {
                        Intent intent = new Intent(DeleteTask.this, activity_task.class);
                        startActivity(intent);
                    }
                }

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
    void init()
    {
        btnRetour = findViewById(R.id.btnRetour);
        btnDeleteTask= findViewById(R.id.btnDeleteTask2);
        etTitleSupp= findViewById(R.id.etTitleSupp);
    }
}