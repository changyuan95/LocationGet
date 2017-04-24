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


public class Track extends AppCompatActivity {

    private TextView phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        //Glide加载图片
        ImageView trackImage = (ImageView)findViewById(R.id.track_image);
        Glide.with(this).load(R.drawable.pineapple).into(trackImage);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_track);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //设定虚浮按钮点击事件
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_settings);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Track.this, Settings.class);
                startActivity(intent);
            }
        });

        phoneNumber = (TextView)findViewById(R.id.textview_phone);
        //获取data数据中的被监控端手机号
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        phoneNumber.setText("当前被监控端手机号为：" + pref.getString("pnumber", ""));

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
        phoneNumber = (TextView)findViewById(R.id.textview_phone);
        //获取data数据中的被监控端手机号
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        phoneNumber.setText("当前监控端手机号为：" + pref.getString("pnumber", ""));
    }
}
