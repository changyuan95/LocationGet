package com.example.locationget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;


public class Locate extends AppCompatActivity{


    private TextView positionText;
    private Button sendText;
    private Button showMap;
    private Button autoSend;

    public String context;

    private String number = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);

        ImageView secondImage = (ImageView)findViewById(R.id.second_image);
        Glide.with(this).load(R.drawable.banana).into(secondImage);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_second);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //读取data数据中的监控端手机号
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        number = pref.getString("number", "");

        context = getIntent().getStringExtra("string_data");
        positionText = (TextView)findViewById(R.id.position_text_view);
        positionText.setText(context);
        sendText = (Button)findViewById(R.id.send_text);
        sendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number != ""){
                    send1(number, context);
                }
                else{
                    Toast.makeText(Locate.this, "请先设置目标手机号", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showMap = (Button)findViewById(R.id.show_map);
        showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Locate.this, ShowMap.class);
                startActivity(intent);
            }
        });
        //设定虚浮按钮点击事件
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Locate.this, Settings.class);
                startActivity(intent);
            }
        });
        autoSend = (Button)findViewById(R.id.auto_send);
        autoSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number != ""){
                    autosend(number, context);
                }
                else{
                    Toast.makeText(Locate.this, "请先设置目标手机号", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void send1(String number, String message){
        Uri uri = Uri.parse("smsto:" + number);
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
        sendIntent.putExtra("sms_body", message);
        startActivity(sendIntent);
    }

    private void autosend(String number, String message){
        if (message != null) {
            SmsManager sms = SmsManager.getDefault();
            List<String> texts = sms.divideMessage(message);
            for (String text : texts) {
                sms.sendTextMessage(number, null, text, null, null);
            }
        }
    }

}
