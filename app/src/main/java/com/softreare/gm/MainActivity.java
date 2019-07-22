package com.softreare.gm;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;
    ArrayAdapter<String> mAdapter;
    ListView listView;
    ImageButton del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//       // setSupportActionBar(toolbar);

        del=(ImageButton)findViewById(R.id.delete);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add);
        dbHelper = new DbHelper(this);
        listView = (ListView) findViewById(R.id.tasklist);
        loadTaskList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText taskedit=new EditText(MainActivity.this);
                AlertDialog dialog=new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Add Your Task")
                        .setMessage("What do you want to do next?")
                        .setView(taskedit)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task=String.valueOf(taskedit.getText());
                                dbHelper.insetNewTask(task);
                                loadTaskList();
                                Toast.makeText(MainActivity.this,"Item added",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                dialog.show();
            }


        });



    }

    private void loadTaskList(){
        ArrayList<String> taskList=dbHelper.getTaskList();
        if(mAdapter==null){
            mAdapter=new ArrayAdapter<String>(this,R.layout.listitem,R.id.task_title,taskList);
            listView.setAdapter(mAdapter);
        }
        else{
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

    public void DeleteTask(View v) {
        View parent=(View)v.getParent();
        TextView taskview=(TextView)parent.findViewById(R.id.task_title);
        String task=String.valueOf(taskview.getText());
        dbHelper.deleteTask(task);
        loadTaskList();
        Toast.makeText(MainActivity.this,"Item Deleted",Toast.LENGTH_SHORT).show();
    }
}

