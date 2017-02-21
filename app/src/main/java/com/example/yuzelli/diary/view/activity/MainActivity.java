package com.example.yuzelli.diary.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzelli.diary.R;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll_time;
    private LinearLayout ll_photo;
    private LinearLayout ll_diary;
    private LinearLayout ll_find;
    private LinearLayout ll_day;
    private TextView tv_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_time = (LinearLayout) this.findViewById(R.id.ll_time);
        ll_photo = (LinearLayout) this.findViewById(R.id.ll_photo);
        ll_diary = (LinearLayout) this.findViewById(R.id.ll_diary);
        ll_find = (LinearLayout) this.findViewById(R.id.ll_find);
        ll_day = (LinearLayout) this.findViewById(R.id.ll_day);
        tv_write = (TextView) this.findViewById(R.id.tv_write);
        ll_time.setOnClickListener(this);
        ll_photo.setOnClickListener(this);
        ll_diary.setOnClickListener(this);
        ll_find.setOnClickListener(this);
        ll_day.setOnClickListener(this);
        tv_write.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_write:
                intent= new Intent(MainActivity.this,WriteActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_time:
                intent= new Intent(MainActivity.this,TimeActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_photo:
                 intent = new Intent(MainActivity.this,PhotoActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_diary:
                 intent = new Intent(MainActivity.this,DiaryActivity.class);
                startActivity(intent);
                break;  case R.id.ll_day:
                 intent = new Intent(MainActivity.this,DayActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_find:
                 intent = new Intent(MainActivity.this,FindActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
