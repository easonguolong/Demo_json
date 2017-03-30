package com.gson_jsondemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.tv_list);
        Gson gson = new Gson();
        ConfigJson configJson = gson.fromJson(readJsonFromAssets(),ConfigJson.class);
        final TvAdapter adapter = new TvAdapter(configJson.GetTvs(),this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = ((TvConfig)adapter.getItem(position)).getTvUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    private String readJsonFromAssets(){
        StringBuilder stringBuilder = new StringBuilder();
        try{
            InputStream inputStream = getAssets().open("tv.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String temp = "";
            temp = reader.readLine();
            while(temp !=null){
                stringBuilder.append(temp).append("\n");
                temp = reader.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new String(stringBuilder);
    }

}
