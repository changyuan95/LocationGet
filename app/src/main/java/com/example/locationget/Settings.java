package com.example.locationget;


import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
    private String number = "";
    private TextView setPhone;
    public static String rate = "";
    private TextView setRate;
    private EditText pNumber;
    private Button ensurePnumber;
    private TextView setNumber;
    private String pnumber = "";


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

         //获取data数据
         SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
         number = pref.getString("number", "");
         pnumber = pref.getString("pnumber", "");

         phoneNumber = (EditText)findViewById(R.id.phone_number);
         ensureNumber = (Button)findViewById(R.id.ensure_number);
         setPhone = (TextView)findViewById(R.id.set_phone);
         //设置TextView
         if(number.equals("")){
             setPhone.setText("     监控端手机号：尚未设置");
         }else{
             setPhone.setText("     监控端手机号：" + number);
         }
         //存储被监控端设置
         ensureNumber.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(!(phoneNumber.getText().toString().trim().equals(""))){
                     number = phoneNumber.getText().toString();
                     SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                     editor.putString("number", phoneNumber.getText().toString());
                     editor.apply();
                     Toast.makeText(Settings.this, "设置成功", Toast.LENGTH_SHORT).show();
                     setPhone.setText("     监控端手机号：" + number);
                 }else{
                     Toast.makeText(Settings.this, "请先输入监控端手机号", Toast.LENGTH_SHORT).show();
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

         //监控端设置
         pNumber = (EditText)findViewById(R.id.edit_number);
         ensurePnumber = (Button)findViewById(R.id.button_number);
         setNumber = (TextView)findViewById(R.id.text_number);
         //设置TextView
         if(pnumber.equals("")){
             setNumber.setText("     被监控端手机号：尚未设置");
         }else{
             setNumber.setText("     被监控端手机号：" + pnumber);
         }
         //存储监控端设置
         ensurePnumber.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(!(pNumber.getText().toString().trim().equals(""))){
                     pnumber = pNumber.getText().toString();
                     SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                     editor.putString("pnumber", pNumber.getText().toString());
                     editor.apply();
                     Toast.makeText(Settings.this, "设置成功", Toast.LENGTH_SHORT).show();
                     setNumber.setText("     被监控端手机号：" + pnumber);
                 }else{
                     Toast.makeText(Settings.this, "请先输入被监控端手机号", Toast.LENGTH_SHORT).show();
                 }
             }
         });

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
}
