package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextThemeWrapper;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView mylist;
    ArrayAdapter adapter;
    private int position;
    private final ArrayList<String> todo_list = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mylist=(ListView)findViewById(R.id.lst_toDoItems);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,todo_list);
        Button addbutton=(Button)findViewById(R.id.button);
        mylist.setAdapter(adapter);
        registerForContextMenu(mylist);
        addbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TextView myitem = (EditText)findViewById(R.id.textView);
                String value = myitem.getText().toString();
               // todo_list.add(value);
                adapter.add(value);
                myitem.setText("");
            }
        });
        mylist.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(), ((TextView)view).getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );


        Button gridButton = (Button)findViewById(R.id.grid_btn);
        gridButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i = new Intent(MainActivity.this, Main2Activity.class);
              startActivity(i);
          }
        }
        );
    }
    @Override
    //Récupération du petit menu Supprimer
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
    }
    @Override
    //Apres appui sur supprimer
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.item1:
                position = (int) info.id;
                todo_list.remove(position);
                this.adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
