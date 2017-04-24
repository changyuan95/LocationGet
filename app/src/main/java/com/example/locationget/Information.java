package com.example.locationget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Information extends AppCompatActivity {

    private TextView personalInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        ImageView informationImage = (ImageView)findViewById(R.id.information_image);
        Glide.with(this).load(R.drawable.grape).into(informationImage);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_i);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //设定悬浮按钮点击事件
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_information);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Information.this, SetInformation.class);
                startActivity(intent);
            }
        });

        personalInformation = (TextView)findViewById(R.id.personal_information);
        //获取data中的个人信息数据
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        StringBuilder personalInfo = new StringBuilder();
        personalInfo.append("家庭住址：").append(pref.getString("homelocation", "")).append("\n\n");
        personalInfo.append("家庭电话：").append(pref.getString("homephone", "")).append("\n\n");
        personalInfo.append("家人手机：").append(pref.getString("familyphone", ""));
        personalInformation.setText(personalInfo.toString());
    }

    //处理HomeAsUp按钮点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        personalInformation = (TextView)findViewById(R.id.personal_information);
        //获取data中的个人信息数据
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        StringBuilder personalInfo = new StringBuilder();
        personalInfo.append("家庭住址：").append(pref.getString("homelocation", "")).append("\n\n");
        personalInfo.append("家庭电话：").append(pref.getString("homephone", "")).append("\n\n");
        personalInfo.append("家人手机：").append(pref.getString("familyphone", ""));
        personalInformation.setText(personalInfo.toString());
    }
}
