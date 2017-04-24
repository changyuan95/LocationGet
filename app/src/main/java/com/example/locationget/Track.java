package com.example.locationget;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaCodec;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.regex.Pattern;


public class Track extends AppCompatActivity {

    private TextView phoneNumber;
    private TextView trackSms;
    private Uri SMS_INBOX = Uri.parse("content://sms/inbox");
    private String pnumber = "";
    private String smsContent = "";

    private Button trackMap;

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

        //处理"在地图上显示位置"按钮点击事件
        trackMap = (Button)findViewById(R.id.track_show_map);
        trackMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(smsContent.equals("")){
                    Toast.makeText(Track.this, "短信不存在", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Track.this, TrackMap.class);
                    //传递短信内容
                    intent.putExtra("string_sms", smsContent);
                    startActivity(intent);
                }
            }
        });

        phoneNumber = (TextView)findViewById(R.id.textview_phone);
        trackSms = (TextView)findViewById(R.id.textview_track) ;

        //获取data数据中的被监控端手机号
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        pnumber = pref.getString("pnumber", "");
        phoneNumber.setText("当前被监控端手机号为：" + pnumber);

        //判断是否设置被监控端手机号
        if(pnumber.equals("")){
            Toast.makeText(Track.this, "请先设置被监控端手机号", Toast.LENGTH_SHORT).show();
        }else {
            getSmsFromPhone(pnumber);
        }

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
        //获取data数据中的被监控端手机号
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        pnumber = pref.getString("pnumber", "");
        phoneNumber.setText("当前被监控端手机号为：" + pnumber);
        if(pnumber.equals("")){
            Toast.makeText(Track.this, "请先设置被监控端手机号", Toast.LENGTH_SHORT).show();
        }else {
            getSmsFromPhone(pnumber);
        }
    }

    //通过短信数据库获取短信
    public void getSmsFromPhone(String pnumber) {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(SMS_INBOX, new String[] { "address", "body" }, null, null, "_id desc limit 1");
        if (null == cursor)
            return;
        if (cursor.moveToNext()) {
            String address = cursor.getString(0);
            if(address.equals(pnumber)){
                smsContent = cursor.getString(1);
                trackSms.setText(smsContent);
            }else{
                trackSms.setText("未查找到" + pnumber + "的短信");
            }

        }

    }


}
