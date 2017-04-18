package com.example.locationget;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Settings extends AppCompatActivity {

    private EditText phoneNumber;
    private EditText sendRate;
    private Button ensureNumber;
    private Button ensureRate;
    public static String number = "";
    private TextView setPhone;
    public static String rate = "";
    private TextView setRate;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

         ImageView settingsImage = (ImageView)findViewById(R.id.settings_image);
         Glide.with(this).load(R.drawable.orange).into(settingsImage);
         Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_s);
         setSupportActionBar(toolbar);
         ActionBar actionBar = getSupportActionBar();
         if(actionBar != null){
             actionBar.setDisplayHomeAsUpEnabled(true);
         }

         phoneNumber = (EditText)findViewById(R.id.phone_number);
         ensureNumber = (Button)findViewById(R.id.ensure_number);
         setPhone = (TextView)findViewById(R.id.set_phone);
         if(number.equals("")){
             setPhone.setText("     手机号：尚未设置");
         }else{
             setPhone.setText("     手机号：" + number);
         }
         ensureNumber.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(!(phoneNumber.getText().toString().trim().equals(""))){
                     number = phoneNumber.getText().toString();
                     Toast.makeText(Settings.this, "设置成功", Toast.LENGTH_SHORT).show();
                     setPhone.setText("     手机号：" + number);
                 }else{
                     Toast.makeText(Settings.this, "请先输入手机号", Toast.LENGTH_SHORT).show();
                 }
             }
         });
         sendRate = (EditText)findViewById(R.id.send_rate);
         ensureRate = (Button)findViewById(R.id.ensure_rate);
         setRate = (TextView)findViewById(R.id.set_rate);
         if(rate.equals("")){
             setRate.setText("     发送频率：尚未设置");
         }else{
             setRate.setText("     发送频率：" + rate);
         }
         ensureRate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(!(sendRate.getText().toString().trim().equals(""))){
                     rate = sendRate.getText().toString();
                     Toast.makeText(Settings.this, "设置成功", Toast.LENGTH_SHORT).show();
                     setRate.setText("     发送频率：" + rate);
                 }else{
                     Toast.makeText(Settings.this, "请先输入频率", Toast.LENGTH_SHORT).show();
                 }
             }
         });

    }
}
