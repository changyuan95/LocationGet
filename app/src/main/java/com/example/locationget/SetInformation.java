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
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class SetInformation extends AppCompatActivity {

    private Button ensure;
    private EditText location;
    private EditText homePhone;
    private EditText familyPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_information);

        //Glide加载图片
        ImageView setinformationImage = (ImageView)findViewById(R.id.setinformation_image);
        Glide.with(this).load(R.drawable.watermelon).into(setinformationImage);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_si);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ensure = (Button)findViewById(R.id.button_ensure);
        location = (EditText)findViewById(R.id.home_location);
        homePhone = (EditText)findViewById(R.id.home_phone);
        familyPhone = (EditText)findViewById(R.id.family_phone);
        //获取data中数据作为EditText的初始值
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        location.setText(pref.getString("homelocation", ""));
        homePhone.setText(pref.getString("homephone", ""));
        familyPhone.setText(pref.getString("familyphone", ""));
        //设定确定按钮点击事件
        ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //存储个人信息
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("homelocation", location.getText().toString());
                editor.putString("homephone", homePhone.getText().toString());
                editor.putString("familyphone", familyPhone.getText().toString());
                editor.apply();
                Toast.makeText(SetInformation.this, "设置成功", Toast.LENGTH_SHORT).show();
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
