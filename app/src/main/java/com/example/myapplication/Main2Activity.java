package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        GridView gridView = (GridView)findViewById(R.id.gridview);

        String myArr[] = getResources().getStringArray(R.array.grid);
        final ArrayList<String> myList = new ArrayList<String>(Arrays.asList(myArr));

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myList);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String letterToReplace = myList.get(position);

                        if(Character.isLowerCase(letterToReplace.charAt(0)))
                            myList.set(position, letterToReplace.toUpperCase());
                        else
                            myList.set(position, letterToReplace.toLowerCase());
                        adapter.notifyDataSetChanged();
                    }
                }
        );
    }
}
